package ir.omidtaheri.foodtest.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import ir.omidtaheri.foodtest.R
import ir.omidtaheri.foodtest.base.BaseFragment
import ir.omidtaheri.foodtest.base.viewmodelutils.GenericSavedStateViewModelFactory
import ir.omidtaheri.foodtest.customview.MultiStatePage
import ir.omidtaheri.foodtest.databinding.FragmentSearchBinding
import ir.omidtaheri.foodtest.di.utils.DaggerInjectUtils
import ir.omidtaheri.foodtest.presentation.search.adapter.SearchAdapter
import ir.omidtaheri.foodtest.presentation.search.di.components.DaggerSearchComponent
import ir.omidtaheri.foodtest.presentation.search.viewmodel.SearchViewModel


class SearchFragment : BaseFragment<SearchViewModel>(), SearchAdapter.Callback {

    private lateinit var multiStatePage: MultiStatePage
    private lateinit var searchbar: TextInputEditText
    private lateinit var recyclerAdapter: SearchAdapter
    private var viewBinding: FragmentSearchBinding? = null

    private val viewModel: SearchViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setSearchbarListeners()
    }

    private fun initRecyclerView() {
        multiStatePage.apply {
            recyclerAdapter = SearchAdapter(requireContext(), this@SearchFragment)
            configRecyclerView(
                recyclerAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>,
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            )
            toDateState()
        }
    }

    private fun setSearchbarListeners() {

        viewModel.setSearchSubjectObserver()

        searchbar.doOnTextChanged { text, _, _, _ ->

            if (checkSearchQuery(text.toString())) {
                search(text.toString())
            } else {
                showToast(getString(R.string.search_fragment_query_blank))
            }
        }

    }

    private fun checkSearchQuery(query: String): Boolean {
        val pattern = Regex("^(\\s)*\$")

        return query.isNotEmpty() &&
                query.isNotBlank() &&
                !pattern.matches(query)
    }

    private fun search(query: String) {
        viewModel.searchSubject.onNext(query)
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        viewBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return viewBinding!!.root
    }

    override fun bindUi() {
        multiStatePage = viewBinding!!.MultiStatePage
        searchbar = viewBinding!!.searchbar
    }

    override fun configDaggerComponent() {
        DaggerSearchComponent
            .builder()
            .applicationComponent(DaggerInjectUtils.provideApplicationComponent(requireContext().applicationContext))
            .build()
            .inject(this)
    }

    override fun setLivaDataObservers() {
        viewModel.foods.observe(this, Observer {
            if (it.isNotEmpty()) {
                multiStatePage.getRecyclerView().layoutManager =
                    GridLayoutManager(context, 2)
                recyclerAdapter.addItems(it)
                multiStatePage.toDateState()
            } else {
                multiStatePage.getRecyclerView().layoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                recyclerAdapter.addItems(it)
                multiStatePage.toDateState()
            }

        })

        viewModel.errorState.observe(this, Observer {
            multiStatePage.toErrorState(
                View.OnClickListener {
                    if (checkSearchQuery(searchbar.text.toString())) {
                        multiStatePage.toLoadingState()
                        viewModel.searchQuery(searchbar.text.toString())
                    } else {
                        showToast(getString(R.string.search_fragment_query_blank))
                    }

                },
                getString(R.string.error_state_error_text),
                getString(R.string.error_state_error_button_text)
            )
        })

        viewModel.isLoading.observe(this, Observer {
            if (it)
                multiStatePage.toLoadingState()
        })

    }


    override fun onItemClick(foodId: Long) {
        val action = SearchFragmentDirections.actionSearchFragmentToFoodDetailFragment(foodId)
        findNavController().navigate(action)
    }


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }


}
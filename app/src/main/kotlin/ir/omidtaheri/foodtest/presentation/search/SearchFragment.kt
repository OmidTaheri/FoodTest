package ir.omidtaheri.foodtest.presentation.search

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
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
    private var stateOfSearchRecyclerview: Parcelable? = null

    private val viewModel: SearchViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        restoreRecyclerViewState()
        setLivaDataObservers()
        setSearchbarListeners()
        setSubjectObserver()
    }

    private fun restoreRecyclerViewState() {
        viewModel.restoreStateOfRecyclerView()?.let {
            stateOfSearchRecyclerview = it
        }
    }

    private fun setSubjectObserver() {
        if (viewModel.foods.value == null)
            viewModel.setSearchSubjectObserver()
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
        searchbar.doOnTextChanged { text, _, _, _ ->
            search(text.toString())
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
        viewModel.foods.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                multiStatePage.getRecyclerView().layoutManager =
                    GridLayoutManager(context, 2)
                recyclerAdapter.addItems(it)
                loadRecyclerViewState()
                multiStatePage.toDateState()

            } else {
                multiStatePage.getRecyclerView().layoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                recyclerAdapter.addItems(it)
                stateOfSearchRecyclerview = null
                multiStatePage.toDateState()
            }

        })

        viewModel.errorState.observe(viewLifecycleOwner, Observer {
            multiStatePage.toErrorState(
                View.OnClickListener {
                    if (checkSearchQuery(searchbar.text.toString())) {
                        multiStatePage.toLoadingState()
                        hideSoftKeyboard(viewBinding!!.root)
                        viewModel.searchQuery(searchbar.text.toString())
                    } else {
                        showToast(getString(R.string.search_fragment_query_blank))
                    }

                },
                getString(R.string.error_state_error_text),
                getString(R.string.error_state_error_button_text)
            )
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                multiStatePage.toLoadingState()
                hideSoftKeyboard(viewBinding!!.root)
            }
        })

    }

    private fun loadRecyclerViewState() {
        stateOfSearchRecyclerview?.let {
            multiStatePage.getRecyclerView().layoutManager?.onRestoreInstanceState(
                it
            )
            stateOfSearchRecyclerview = null
        }
    }


    override fun onItemClick(foodId: Long) {
        val action = SearchFragmentDirections.actionSearchFragmentToFoodDetailFragment(foodId)
        findNavController().navigate(action)
    }


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {

        val searchRecyclerViewState =
            multiStatePage.getRecyclerView().layoutManager?.onSaveInstanceState()

        viewModel.saveStateOfRecyclerView(
            searchRecyclerViewState as LinearLayoutManager.SavedState?
        )

        super.onDestroyView()
        viewBinding = null
    }

    private fun hideSoftKeyboard(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
}
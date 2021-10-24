package ir.omidtaheri.foodtest.presentation.categorylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.omidtaheri.foodtest.R
import ir.omidtaheri.foodtest.base.BaseFragment
import ir.omidtaheri.foodtest.base.viewmodelutils.GenericSavedStateViewModelFactory
import ir.omidtaheri.foodtest.customview.MultiStatePage
import ir.omidtaheri.foodtest.databinding.FragmentCategoryListBinding
import ir.omidtaheri.foodtest.di.utils.DaggerInjectUtils
import ir.omidtaheri.foodtest.presentation.categorylist.adapter.CategoryListAdapter
import ir.omidtaheri.foodtest.presentation.categorylist.di.components.DaggerCategoryComponent
import ir.omidtaheri.foodtest.presentation.categorylist.viewmodel.CategoryViewModel


class CategoryListFragment : BaseFragment<CategoryViewModel>(), CategoryListAdapter.Callback {

    private lateinit var multiStatePage: MultiStatePage
    private lateinit var recyclerAdapter: CategoryListAdapter
    private var viewBinding: FragmentCategoryListBinding? = null

    private val viewModel: CategoryViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        fetchData()
    }

    private fun initRecyclerView() {
        multiStatePage.apply {
            toLoadingState()
            recyclerAdapter = CategoryListAdapter(requireContext(), this@CategoryListFragment)
            configRecyclerView(
                recyclerAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>,
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            )
        }
    }

    private fun fetchData() {
        viewModel.getCategoryList()
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        viewBinding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return viewBinding!!.root
    }

    override fun bindUi() {
        multiStatePage = viewBinding!!.MultiStatePage
    }

    override fun configDaggerComponent() {
        DaggerCategoryComponent
            .builder()
            .applicationComponent(DaggerInjectUtils.provideApplicationComponent(requireContext().applicationContext))
            .build()
            .inject(this)
    }

    override fun setLivaDataObservers() {
        viewModel.categoryItems.observe(this, Observer {
            recyclerAdapter.addItems(it)
            multiStatePage.toDateState()
        })

        viewModel.errorState.observe(this, Observer { errorMessage ->
            multiStatePage.toErrorState(
                View.OnClickListener {
                    multiStatePage.toLoadingState()
                    fetchData()
                },
                getString(R.string.error_state_error_text),
                getString(R.string.error_state_error_button_text)
            )
        })
    }


    override fun onItemClick(categoryId: Long, categoryName: String) {
        val action =
            CategoryListFragmentDirections.actionCategoryListFragmentToFoodListFragment(
                categoryId,
                categoryName
            )

        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }


}
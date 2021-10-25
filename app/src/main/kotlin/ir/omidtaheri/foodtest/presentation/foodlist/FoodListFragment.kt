package ir.omidtaheri.foodtest.presentation.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.omidtaheri.foodtest.R
import ir.omidtaheri.foodtest.base.BaseFragment
import ir.omidtaheri.foodtest.base.viewmodelutils.GenericSavedStateViewModelFactory
import ir.omidtaheri.foodtest.customview.MultiStatePage
import ir.omidtaheri.foodtest.databinding.FragmentFoodListBinding
import ir.omidtaheri.foodtest.di.utils.DaggerInjectUtils
import ir.omidtaheri.foodtest.presentation.MainActivityViewModel
import ir.omidtaheri.foodtest.presentation.foodlist.adapter.FoodListAdapter
import ir.omidtaheri.foodtest.presentation.foodlist.di.components.DaggerFoodListComponent
import ir.omidtaheri.foodtest.presentation.foodlist.viewmodel.FoodListViewModel


class FoodListFragment : BaseFragment<FoodListViewModel>(), FoodListAdapter.Callback {

    private lateinit var multiStatePage: MultiStatePage
    private lateinit var recyclerAdapter: FoodListAdapter
    private var viewBinding: FragmentFoodListBinding? = null
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val viewModel: FoodListViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }

    private val args: FoodListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.setToolbarTitle(args.categoryName)
        initRecyclerView()
        fetchData(args.categoryId)
    }

    private fun initRecyclerView() {
        multiStatePage.apply {
            toLoadingState()
            recyclerAdapter = FoodListAdapter(requireContext(), this@FoodListFragment)
            configRecyclerView(
                recyclerAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>,
                GridLayoutManager(context, 2)
            )
        }
    }

    private fun fetchData(categoryId: Long) {
        viewModel.getFoodList(categoryId)
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        viewBinding = FragmentFoodListBinding.inflate(inflater, container, false)
        return viewBinding!!.root
    }

    override fun bindUi() {
        multiStatePage = viewBinding!!.MultiStatePage
    }

    override fun configDaggerComponent() {
        DaggerFoodListComponent
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
                    multiStatePage.toLoadingState()
                    fetchData(args.categoryId)
                },
                getString(R.string.error_state_error_text),
                getString(R.string.error_state_error_button_text)
            )
        })
    }


    override fun onItemClick(foodId: Long) {
        val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodId)
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }


}
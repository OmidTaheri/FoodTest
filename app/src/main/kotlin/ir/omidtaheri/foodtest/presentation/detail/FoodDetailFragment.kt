package ir.omidtaheri.foodtest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ir.omidtaheri.foodtest.base.BaseFragment
import ir.omidtaheri.foodtest.base.viewmodelutils.GenericSavedStateViewModelFactory
import ir.omidtaheri.foodtest.databinding.FragmentFoodDetailBinding
import ir.omidtaheri.foodtest.di.utils.DaggerInjectUtils
import ir.omidtaheri.foodtest.presentation.detail.di.components.DaggerFoodDetailComponent
import ir.omidtaheri.foodtest.presentation.detail.viewmodel.FoodDetailViewModel


class FoodDetailFragment : BaseFragment<FoodDetailViewModel>() {


    private var viewBinding: FragmentFoodDetailBinding? = null

    private val viewModel: FoodDetailViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }
    private val args: FoodDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData(args.foodId)
    }

    private fun fetchData(foodId: Long) {
        viewModel.getFoodDetail(foodId)
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        viewBinding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return viewBinding!!.root
    }

    override fun bindUi() {
    }

    override fun configDaggerComponent() {
        DaggerFoodDetailComponent
            .builder()
            .applicationComponent(DaggerInjectUtils.provideApplicationComponent(requireContext().applicationContext))
            .build()
            .inject(this)
    }

    override fun setLivaDataObservers() {
        viewModel.foodDetail.observe(this, Observer {
        })

        viewModel.errorMessage.observe(this, Observer {
            showSnackBar(it)
        })
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(viewBinding!!.root, message, BaseTransientBottomBar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }


}
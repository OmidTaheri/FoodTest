package ir.omidtaheri.foodtest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ir.omidtaheri.foodtest.base.BaseFragment
import ir.omidtaheri.foodtest.base.glideutils.loadFoodImage
import ir.omidtaheri.foodtest.base.viewmodelutils.GenericSavedStateViewModelFactory
import ir.omidtaheri.foodtest.data.models.FoodMaterial
import ir.omidtaheri.foodtest.databinding.FragmentFoodDetailBinding
import ir.omidtaheri.foodtest.di.utils.DaggerInjectUtils
import ir.omidtaheri.foodtest.presentation.MainActivityViewModel
import ir.omidtaheri.foodtest.presentation.detail.adapter.MaterialListAdapter
import ir.omidtaheri.foodtest.presentation.detail.di.components.DaggerFoodDetailComponent
import ir.omidtaheri.foodtest.presentation.detail.viewmodel.FoodDetailViewModel


class FoodDetailFragment : BaseFragment<FoodDetailViewModel>() {

    private lateinit var foodImage: ImageView
    private lateinit var foodName: TextView
    private lateinit var foodCategory: TextView
    private lateinit var foodRecipe: TextView
    private lateinit var foodMaterialRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: MaterialListAdapter

    private var viewBinding: FragmentFoodDetailBinding? = null

    private val viewModel: FoodDetailViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val args: FoodDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        fetchData(args.foodId)
    }

    private fun initRecyclerView() {
        recyclerAdapter = MaterialListAdapter(requireContext())
        foodMaterialRecyclerView.adapter = recyclerAdapter
        foodMaterialRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    }

    private fun fetchData(foodId: Long) {
        if(viewModel.foodDetail.value ==null)
        viewModel.getFoodDetail(foodId)
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        viewBinding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return viewBinding!!.root
    }

    override fun bindUi() {
        foodImage = viewBinding!!.foodImageview
        foodName = viewBinding!!.name
        foodCategory = viewBinding!!.category
        foodRecipe = viewBinding!!.recipeText
        foodMaterialRecyclerView = viewBinding!!.materialRecyclerview
    }

    override fun configDaggerComponent() {
        DaggerFoodDetailComponent
            .builder()
            .applicationComponent(DaggerInjectUtils.provideApplicationComponent(requireContext().applicationContext))
            .build()
            .inject(this)
    }

    override fun setLivaDataObservers() {
        viewModel.foodDetail.observe(this, Observer { food ->

            food.imageUrl?.let { foodImage.loadFoodImage(food.imageUrl, requireContext()) }
            foodName.text = food.title
            foodCategory.text = food.categoryName
            foodRecipe.text = food.recipe
            recyclerAdapter.addItems(getListOfMaterials(food.materials))
            activityViewModel.setToolbarTitle(food.title)
        })

        viewModel.errorMessage.observe(this, Observer {
            showSnackBar(it)
        })
    }

    private fun getListOfMaterials(materials: String): List<FoodMaterial> {
        val pattern = Regex("(\\n)")
        val lines = materials.split(pattern)
        return lines.map {
            val materialItems = it.split(":")
            FoodMaterial(materialItems[0], materialItems[1])
        }
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(viewBinding!!.root, message, BaseTransientBottomBar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}
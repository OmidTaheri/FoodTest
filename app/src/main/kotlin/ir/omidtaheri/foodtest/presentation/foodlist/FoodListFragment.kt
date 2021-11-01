package ir.omidtaheri.foodtest.presentation.foodlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import ir.omidtaheri.foodtest.R
import ir.omidtaheri.foodtest.base.BaseFragment
import ir.omidtaheri.foodtest.base.viewmodelutils.GenericSavedStateViewModelFactory
import ir.omidtaheri.foodtest.customview.MultiStatePage
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import ir.omidtaheri.foodtest.data.models.FoodModel
import ir.omidtaheri.foodtest.databinding.FragmentFoodListBinding
import ir.omidtaheri.foodtest.di.utils.DaggerInjectUtils
import ir.omidtaheri.foodtest.presentation.MainActivityViewModel
import ir.omidtaheri.foodtest.presentation.foodlist.adapter.FoodListAdapter
import ir.omidtaheri.foodtest.presentation.foodlist.di.components.DaggerFoodListComponent
import ir.omidtaheri.foodtest.presentation.foodlist.viewmodel.FoodListViewModel
import java.util.regex.Pattern


class FoodListFragment : BaseFragment<FoodListViewModel>(),
    FoodListAdapter.Callback {


    private lateinit var bottomSheetSaveBtn: Button
    private lateinit var foodCategoryEditText: TextInputEditText
    private lateinit var foodMaterialsEditText: TextInputEditText
    private lateinit var foodRecipeEditText: TextInputEditText
    private lateinit var foodNameEditText: TextInputEditText
    private lateinit var fab: FloatingActionButton
    private lateinit var multiStatePage: MultiStatePage
    private lateinit var sheetBehavior: BottomSheetBehavior<NestedScrollView>
    private lateinit var bottomSheetClose: ImageView
    private lateinit var bottomSheet: NestedScrollView

    private lateinit var recyclerAdapter: FoodListAdapter
    private var viewBinding: FragmentFoodListBinding? = null
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var recentlyAddedItem: FoodDetailModel

    private val viewModel: FoodListViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }

    private val args: FoodListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.setToolbarTitle(args.categoryName)
        initRecyclerView()
        setLivaDataObservers()
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
        fab = viewBinding!!.fabButton
        setFabClickListener()
        bindUiComponentBottomSheet()
    }

    private fun setFabClickListener() {
        fab.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        }
    }

    private fun bindUiComponentBottomSheet() {
        bottomSheet = viewBinding!!.bottomSheetLayout.root
        bottomSheetClose = viewBinding!!.bottomSheetLayout.closeButton
        foodNameEditText = viewBinding!!.bottomSheetLayout.foodNameEditText
        foodRecipeEditText = viewBinding!!.bottomSheetLayout.foodRecipeEditText
        foodMaterialsEditText = viewBinding!!.bottomSheetLayout.foodMaterialsEditText
        foodCategoryEditText = viewBinding!!.bottomSheetLayout.foodCategoryEditText
        foodCategoryEditText.setText(args.categoryName)
        bottomSheetSaveBtn = viewBinding!!.bottomSheetLayout.saveBtn
        setBottomSheetCallbacks()
    }

    private fun setBottomSheetCallbacks() {
        sheetBehavior = BottomSheetBehavior.from(bottomSheet)
        sheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        fab.show()
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        fab.hide()

                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        fab.hide()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetClose.setOnClickListener {
            resetBottomSheet()
        }

        bottomSheetSaveBtn.setOnClickListener {
            if (isBlankForm()) {
                showSnackBar(getString(R.string.bottom_sheet_blank_form_message))
                return@setOnClickListener
            }

            if (!checkMaterialsPattern()) {
                showSnackBar(getString(R.string.bottom_sheet_invalid_pattern_message))
                return@setOnClickListener
            }
            recentlyAddedItem = createFoodDetailModel()
            viewModel.insertFood(recentlyAddedItem)
        }
    }

    private fun isBlankForm(): Boolean {
        return (foodNameEditText.text.toString().isBlank() ||
                foodRecipeEditText.text.toString().isBlank() ||
                foodMaterialsEditText.text.toString().isBlank() ||
                foodCategoryEditText.text.toString().isBlank())
    }

    private fun checkMaterialsPattern(): Boolean {
        val patternNewLine = Regex("(\\n)")
        val patternFoodMaterials = Pattern.compile("^[^:]+:[^:]+\$").toRegex()
        val text = foodMaterialsEditText.text.toString()
        val lines = text.split(patternNewLine)
        val lineStatus = mutableListOf<Boolean>()

        lines.forEach {
            if (it.matches(patternFoodMaterials))
                lineStatus.add(true)
            else
                lineStatus.add(false)
        }

        return lineStatus.all {
            it == true
        }
    }

    private fun createFoodDetailModel(): FoodDetailModel {
        return FoodDetailModel(
            foodNameEditText.text.toString(),
            0,
            foodCategoryEditText.text.toString(),
            args.categoryId,
            "",
            foodRecipeEditText.text.toString(),
            foodMaterialsEditText.text.toString()
        )
    }

    private fun createFoodModel(foodDetailModel: FoodDetailModel, foodId: Long): FoodModel {
        return FoodModel(
            foodDetailModel.title,
            foodId,
            foodDetailModel.categoryName,
            foodDetailModel.imageUrl
        )
    }

    private fun resetBottomSheet() {
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        foodNameEditText.setText("")
        foodNameEditText.clearFocus()
        foodRecipeEditText.setText("")
        foodRecipeEditText.clearFocus()
        foodMaterialsEditText.setText("")
        foodMaterialsEditText.clearFocus()
        hideSoftKeyboard(viewBinding!!.root)
    }


    override fun configDaggerComponent() {
        DaggerFoodListComponent
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
                multiStatePage.toDateState()
            } else {
                multiStatePage.getRecyclerView().layoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                recyclerAdapter.addItems(it)
                multiStatePage.toDateState()
            }

        })

        viewModel.errorState.observe(viewLifecycleOwner, Observer {
            multiStatePage.toErrorState(
                View.OnClickListener {
                    multiStatePage.toLoadingState()
                    fetchData(args.categoryId)
                },
                getString(R.string.error_state_error_text),
                getString(R.string.error_state_error_button_text)
            )
        })

        viewModel.addedFoodErrorMessage.observe(viewLifecycleOwner, Observer {
            showSnackBar(getString(R.string.insert_food_error_message))
        })

        viewModel.addedFood.observe(viewLifecycleOwner, Observer {
            showSnackBar(getString(R.string.insert_food_success_message))
            resetBottomSheet()
            recyclerAdapter.addItem(createFoodModel(recentlyAddedItem, it))
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

    private fun hideSoftKeyboard(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(viewBinding!!.root, message, BaseTransientBottomBar.LENGTH_LONG).show()
    }

}
package ir.omidtaheri.foodtest.presentation.categorylist.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import ir.omidtaheri.foodtest.data.repository.IFoodCategoryRepository

import javax.inject.Inject

class CategoryViewModelFactory @Inject constructor(
    private val foodCategoryRepository: IFoodCategoryRepository,
    private val rxSchedulers: BaseSchedulers,
    private val application: Application
) : ViewModelAssistedFactory<CategoryViewModel> {

    override fun create(handle: SavedStateHandle): CategoryViewModel {
        return CategoryViewModel(
            foodCategoryRepository,
            rxSchedulers,
            handle,
            application
        )
    }

}

package ir.omidtaheri.foodtest.presentation.foodlist.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import ir.omidtaheri.foodtest.data.repository.IFoodCategoryRepository
import ir.omidtaheri.foodtest.data.repository.IFoodRepository

import javax.inject.Inject

class FoodListViewModelFactory @Inject constructor(
    private val foodCategoryRepository: IFoodCategoryRepository,
    private val foodRepository: IFoodRepository,
    private val rxSchedulers: BaseSchedulers,
    private val application: Application
) : ViewModelAssistedFactory<FoodListViewModel> {

    override fun create(handle: SavedStateHandle): FoodListViewModel {
        return FoodListViewModel(
            foodCategoryRepository,
            foodRepository,
            rxSchedulers,
            handle,
            application
        )
    }

}

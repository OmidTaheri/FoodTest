package ir.omidtaheri.foodtest.presentation.detail.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import ir.omidtaheri.foodtest.data.repository.IFoodRepository
import javax.inject.Inject

class FoodDetailViewModelFactory @Inject constructor(
    private val foodRepository: IFoodRepository,
    private val rxSchedulers: BaseSchedulers,
    private val application: Application
) : ViewModelAssistedFactory<FoodDetailViewModel> {

    override fun create(handle: SavedStateHandle): FoodDetailViewModel {
        return FoodDetailViewModel(
            foodRepository,
            rxSchedulers,
            handle,
            application
        )
    }

}

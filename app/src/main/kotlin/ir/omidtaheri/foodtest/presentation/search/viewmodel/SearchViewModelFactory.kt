package ir.omidtaheri.foodtest.presentation.search.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import ir.omidtaheri.foodtest.data.repository.IFoodRepository
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val foodRepository: IFoodRepository,
    private val rxSchedulers: BaseSchedulers,
    private val application: Application
) : ViewModelAssistedFactory<SearchViewModel> {

    override fun create(handle: SavedStateHandle): SearchViewModel {
        return SearchViewModel(
            foodRepository,
            rxSchedulers,
            handle,
            application
        )
    }

}

package ir.omidtaheri.foodtest.presentation.foodlist.di.modules

import dagger.Binds
import dagger.Module
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import ir.omidtaheri.foodtest.di.scopes.FragmentScope
import ir.omidtaheri.foodtest.presentation.foodlist.viewmodel.FoodListViewModel
import ir.omidtaheri.foodtest.presentation.foodlist.viewmodel.FoodListViewModelFactory

@Module
interface FoodListModule {

    @FragmentScope
    @Binds
    fun provideFoodListViewModelFactory(viewModelFactory: FoodListViewModelFactory): ViewModelAssistedFactory<FoodListViewModel>
}

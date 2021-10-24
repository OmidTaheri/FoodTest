package ir.omidtaheri.foodtest.presentation.detail.di.modules

import dagger.Binds
import dagger.Module
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import ir.omidtaheri.foodtest.di.scopes.FragmentScope
import ir.omidtaheri.foodtest.presentation.detail.viewmodel.FoodDetailViewModel
import ir.omidtaheri.foodtest.presentation.detail.viewmodel.FoodDetailViewModelFactory

@Module
interface FoodDetailModule {

    @FragmentScope
    @Binds
    fun provideFoodDetailViewModelFactory(viewModelFactory: FoodDetailViewModelFactory): ViewModelAssistedFactory<FoodDetailViewModel>
}

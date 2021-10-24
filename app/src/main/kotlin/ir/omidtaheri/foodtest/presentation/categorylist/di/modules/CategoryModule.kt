package ir.omidtaheri.foodtest.presentation.categorylist.di.modules

import dagger.Binds
import dagger.Module
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import ir.omidtaheri.foodtest.di.scopes.FragmentScope
import ir.omidtaheri.foodtest.presentation.categorylist.viewmodel.CategoryViewModel
import ir.omidtaheri.foodtest.presentation.categorylist.viewmodel.CategoryViewModelFactory

@Module
interface CategoryModule {

    @FragmentScope
    @Binds
    fun provideCategoryViewModelFactory(viewModelFactory: CategoryViewModelFactory): ViewModelAssistedFactory<CategoryViewModel>
}

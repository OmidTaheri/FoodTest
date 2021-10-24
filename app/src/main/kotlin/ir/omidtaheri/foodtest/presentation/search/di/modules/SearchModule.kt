package ir.omidtaheri.foodtest.presentation.search.di.modules

import dagger.Binds
import dagger.Module
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import ir.omidtaheri.foodtest.di.scopes.FragmentScope
import ir.omidtaheri.foodtest.presentation.search.viewmodel.SearchViewModel
import ir.omidtaheri.foodtest.presentation.search.viewmodel.SearchViewModelFactory

@Module
interface SearchModule {

    @FragmentScope
    @Binds
    fun provideSearchViewModelFactory(viewModelFactory: SearchViewModelFactory): ViewModelAssistedFactory<SearchViewModel>
}

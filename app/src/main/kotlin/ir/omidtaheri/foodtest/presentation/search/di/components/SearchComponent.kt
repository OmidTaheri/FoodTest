package ir.omidtaheri.foodtest.presentation.search.di.components

import dagger.Component
import ir.omidtaheri.foodtest.di.components.ApplicationComponent
import ir.omidtaheri.foodtest.di.scopes.FragmentScope
import ir.omidtaheri.foodtest.presentation.search.SearchFragment
import ir.omidtaheri.foodtest.presentation.search.di.modules.SearchModule


@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [SearchModule::class])
interface SearchComponent {
    fun inject(fragment: SearchFragment)
}

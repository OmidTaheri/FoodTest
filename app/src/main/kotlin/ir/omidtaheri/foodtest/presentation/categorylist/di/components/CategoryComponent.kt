package ir.omidtaheri.foodtest.presentation.categorylist.di.components

import dagger.Component
import ir.omidtaheri.foodtest.di.components.ApplicationComponent
import ir.omidtaheri.foodtest.di.scopes.FragmentScope
import ir.omidtaheri.foodtest.presentation.categorylist.CategoryListFragment
import ir.omidtaheri.foodtest.presentation.categorylist.di.modules.CategoryModule


@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [CategoryModule::class])
interface CategoryComponent {
    fun inject(fragment: CategoryListFragment)
}

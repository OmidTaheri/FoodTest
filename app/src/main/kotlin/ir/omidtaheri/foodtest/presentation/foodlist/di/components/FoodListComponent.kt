package ir.omidtaheri.foodtest.presentation.foodlist.di.components

import dagger.Component
import ir.omidtaheri.foodtest.di.components.ApplicationComponent
import ir.omidtaheri.foodtest.di.scopes.FragmentScope
import ir.omidtaheri.foodtest.presentation.foodlist.FoodListFragment
import ir.omidtaheri.foodtest.presentation.foodlist.di.modules.FoodListModule


@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FoodListModule::class])
interface FoodListComponent {
    fun inject(fragment: FoodListFragment)
}

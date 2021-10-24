package ir.omidtaheri.foodtest.presentation.detail.di.components

import dagger.Component
import ir.omidtaheri.foodtest.di.components.ApplicationComponent
import ir.omidtaheri.foodtest.di.scopes.FragmentScope
import ir.omidtaheri.foodtest.presentation.detail.FoodDetailFragment
import ir.omidtaheri.foodtest.presentation.detail.di.modules.FoodDetailModule


@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FoodDetailModule::class])
interface FoodDetailComponent {
    fun inject(fragment: FoodDetailFragment)
}

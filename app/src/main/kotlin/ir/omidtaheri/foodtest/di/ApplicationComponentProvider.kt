package ir.omidtaheri.foodtest.di

import ir.omidtaheri.foodtest.di.components.ApplicationComponent


interface ApplicationComponentProvider {
    fun provideApplicationComponent(): ApplicationComponent
}

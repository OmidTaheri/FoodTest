package ir.omidtaheri.foodtest.di.modules

import dagger.Binds
import dagger.Module
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.di.schedulers.AppScheduler
import javax.inject.Singleton

@Module
interface SchedulersModule {

    @Singleton
    @Binds
    fun provideSchedulers(schedulers: AppScheduler): BaseSchedulers

}
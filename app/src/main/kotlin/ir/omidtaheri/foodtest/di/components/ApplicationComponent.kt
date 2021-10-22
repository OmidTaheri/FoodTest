package ir.omidtaheri.foodtest.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.data.repository.IFoodCategoryRepository
import ir.omidtaheri.foodtest.data.repository.IFoodRepository
import ir.omidtaheri.foodtest.di.modules.ApplicationModule
import ir.omidtaheri.foodtest.di.modules.LocalDataSourceModule
import ir.omidtaheri.foodtest.di.modules.RepositoryModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, LocalDataSourceModule::class, RepositoryModule::class])
interface ApplicationComponent {

    fun inject(applicationClass: Application)
    fun schedulers(): BaseSchedulers
    fun foodCategoryRepository(): IFoodCategoryRepository
    fun foodRepository(): IFoodRepository
    fun application(): Application

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            @BindsInstance @Named("dbName") dbName: String,
        ): ApplicationComponent
    }
}

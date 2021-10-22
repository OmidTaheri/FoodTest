package ir.omidtaheri.foodtest.di.modules

import dagger.Binds
import dagger.Module
import ir.omidtaheri.foodtest.data.local.datasource.IFoodCategoryLocalDataSource
import ir.omidtaheri.foodtest.data.local.datasource.FoodCategoryLocalDataSourceImpl
import ir.omidtaheri.foodtest.data.local.datasource.IFoodLocalDataSource
import ir.omidtaheri.foodtest.data.local.datasource.FoodLocalDataSourceImpl
import javax.inject.Singleton

@Module(includes = [LocalModule::class, MapperModule::class])
interface LocalDataSourceModule {

    @Singleton
    @Binds
    fun provideFoodCategoryLocalDataSource(
        datasource: FoodCategoryLocalDataSourceImpl
    ): IFoodCategoryLocalDataSource


    @Singleton
    @Binds
    fun provideFoodLocalDataSource(
        datasource: FoodLocalDataSourceImpl
    ): IFoodLocalDataSource
}

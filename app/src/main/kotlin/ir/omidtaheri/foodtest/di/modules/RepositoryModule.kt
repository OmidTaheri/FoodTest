package ir.omidtaheri.foodtest.di.modules

import dagger.Binds
import dagger.Module
import ir.omidtaheri.foodtest.data.repository.IFoodCategoryRepository
import ir.omidtaheri.foodtest.data.repository.FoodCategoryRepositoryImpl
import ir.omidtaheri.foodtest.data.repository.IFoodRepository
import ir.omidtaheri.foodtest.data.repository.FoodRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideFoodCategoryRepository(
        repository: FoodCategoryRepositoryImpl
    ): IFoodCategoryRepository


    @Singleton
    @Binds
    fun provideFoodRepository(
        repository: FoodRepositoryImpl
    ): IFoodRepository


}

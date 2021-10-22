package ir.omidtaheri.foodtest.data.local.datasource

import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.models.FoodCategoryModel
import ir.omidtaheri.foodtest.data.models.FoodModel

interface IFoodCategoryLocalDataSource {
    fun insertFoodCategory(foodCategory: FoodCategoryModel): Single<Long>
    fun getAllFoodCategories(): Single<List<FoodCategoryModel>>
    fun getAllFoodsByCategory(categoryId: Long): Single<List<FoodModel>>
}
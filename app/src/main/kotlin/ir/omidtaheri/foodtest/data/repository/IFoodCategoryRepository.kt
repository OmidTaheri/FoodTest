package ir.omidtaheri.foodtest.data.repository

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.datastate.DataState
import ir.omidtaheri.foodtest.data.models.FoodCategoryModel
import ir.omidtaheri.foodtest.data.models.FoodModel

interface IFoodCategoryRepository {
    fun insertFoodCategory(foodCategory: FoodCategoryModel): Single<DataState<Long>>
    fun getAllFoodCategories(): Maybe<DataState<List<FoodCategoryModel>>>
    fun getAllFoodsByCategory(categoryId: Long): Maybe<DataState<List<FoodModel>>>
}
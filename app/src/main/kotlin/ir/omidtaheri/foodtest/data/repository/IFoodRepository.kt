package ir.omidtaheri.foodtest.data.repository

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.datastate.DataState
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import ir.omidtaheri.foodtest.data.models.FoodModel

interface IFoodRepository {
    fun insertFood(food: FoodDetailModel): Single<DataState<Long>>
    fun getFoodDetail(foodId: Long): Maybe<DataState<FoodDetailModel>>
    fun searchFood(query: String): Maybe<DataState<List<FoodModel>>>
}
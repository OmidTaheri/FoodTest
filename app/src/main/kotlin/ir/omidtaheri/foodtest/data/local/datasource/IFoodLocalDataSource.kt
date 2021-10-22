package ir.omidtaheri.foodtest.data.local.datasource

import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import ir.omidtaheri.foodtest.data.models.FoodModel

interface IFoodLocalDataSource {
    fun insertFood(food: FoodDetailModel): Single<Long>
    fun getFoodDetail(foodId: Long): Single<FoodDetailModel>
    fun searchFood(query: String): Single<List<FoodModel>>
}
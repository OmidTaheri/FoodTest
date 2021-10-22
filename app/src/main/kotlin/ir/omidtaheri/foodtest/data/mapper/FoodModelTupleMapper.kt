package ir.omidtaheri.foodtest.data.mapper

import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodTuple
import ir.omidtaheri.foodtest.data.models.FoodModel
import javax.inject.Inject


class FoodModelTupleMapper @Inject constructor() :
    IEntityModelMapper<FoodTuple, FoodModel> {
    override fun mapFromEntity(from: FoodTuple): FoodModel {
        return FoodModel(from.title, from.id, from.categoryName, from.imageUrl)
    }
}

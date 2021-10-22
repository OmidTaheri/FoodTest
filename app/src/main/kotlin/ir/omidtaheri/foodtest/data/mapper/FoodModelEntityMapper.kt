package ir.omidtaheri.foodtest.data.mapper

import ir.omidtaheri.foodtest.data.local.entity.FoodEntity
import ir.omidtaheri.foodtest.data.models.FoodModel
import javax.inject.Inject


class FoodModelEntityMapper @Inject constructor() :
    IEntityModelByParamMapper<FoodEntity, FoodModel, String> {
    override fun mapFromEntity(from: FoodEntity, categoryName: String): FoodModel {
        return FoodModel(from.title, from.id, categoryName, from.imageUrl)
    }
}

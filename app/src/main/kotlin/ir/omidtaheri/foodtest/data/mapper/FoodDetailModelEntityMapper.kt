package ir.omidtaheri.foodtest.data.mapper

import ir.omidtaheri.foodtest.data.local.entity.FoodEntity
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import javax.inject.Inject


class FoodDetailModelEntityMapper @Inject constructor() :
    IModelEntityMapper<FoodDetailModel, FoodEntity> {
    override fun mapToEntity(from: FoodDetailModel): FoodEntity {
        //id is auto generated
        return FoodEntity(
            from.title,
            from.id,
            from.categoryId,
            from.imageUrl,
            from.recipe,
            from.materials
        )
    }


}

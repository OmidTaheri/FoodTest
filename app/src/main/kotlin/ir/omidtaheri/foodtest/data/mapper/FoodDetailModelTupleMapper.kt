package ir.omidtaheri.foodtest.data.mapper

import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodDetailTuple
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import javax.inject.Inject


class FoodDetailModelTupleMapper @Inject constructor() :
    IEntityModelMapper<FoodDetailTuple, FoodDetailModel> {
    override fun mapFromEntity(from: FoodDetailTuple): FoodDetailModel {
        return FoodDetailModel(
            from.title,
            from.foodId,
            from.categoryName,
            from.categoryId,
            from.imageUrl,
            from.recipe,
            from.materials
        )
    }

}


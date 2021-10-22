package ir.omidtaheri.foodtest.data.mapper

import ir.omidtaheri.foodtest.data.local.entity.FoodCategoryEntity
import ir.omidtaheri.foodtest.data.models.FoodCategoryModel
import javax.inject.Inject

class FoodCategoryModelEntityMapper @Inject constructor() :
    IModelEntityMapper<FoodCategoryModel, FoodCategoryEntity>,
    IEntityModelMapper<FoodCategoryEntity, FoodCategoryModel> {
    override fun mapFromEntity(from: FoodCategoryEntity): FoodCategoryModel {
        return FoodCategoryModel(from.name,from.id)
    }

    override fun mapToEntity(from: FoodCategoryModel): FoodCategoryEntity {
        //id is auto generate
        return FoodCategoryEntity(from.name,from.id)
    }

}

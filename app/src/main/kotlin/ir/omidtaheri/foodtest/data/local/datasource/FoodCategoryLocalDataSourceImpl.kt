package ir.omidtaheri.foodtest.data.local.datasource

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.local.dao.FoodCategoryDao
import ir.omidtaheri.foodtest.data.local.entity.FoodCategoryEntity
import ir.omidtaheri.foodtest.data.local.entity.FoodEntity
import ir.omidtaheri.foodtest.data.mapper.IEntityModelByParamMapper
import ir.omidtaheri.foodtest.data.mapper.IEntityModelMapper
import ir.omidtaheri.foodtest.data.mapper.IModelEntityMapper
import ir.omidtaheri.foodtest.data.models.FoodCategoryModel
import ir.omidtaheri.foodtest.data.models.FoodModel
import javax.inject.Inject
import javax.inject.Named


class FoodCategoryLocalDataSourceImpl @Inject constructor(
    private val foodCategoryDao: FoodCategoryDao,
    @Named("FoodCategoryModelEntityMapper")
    private val foodCategoryModelEntityMapper: IModelEntityMapper<FoodCategoryModel, FoodCategoryEntity>,
    @Named("FoodCategoryEntityModelMapper")
    private val foodCategoryEntityModelMapper: IEntityModelMapper<FoodCategoryEntity, FoodCategoryModel>,
    @Named("FoodEntityModelMapper")
    private val foodEntityModelMapper: IEntityModelByParamMapper<FoodEntity, FoodModel, String>
) : IFoodCategoryLocalDataSource {

    override fun insertFoodCategory(foodCategory: FoodCategoryModel): Single<Long> {
        return foodCategoryDao.insertFoodCategory(
            foodCategoryModelEntityMapper.mapToEntity(
                foodCategory
            )
        )
    }

    override fun getAllFoodCategories(): Maybe<List<FoodCategoryModel>> {
        return foodCategoryDao.getAllFoodCategories().map { list ->
            list.map {
                foodCategoryEntityModelMapper.mapFromEntity(it)
            }
        }
    }

    override fun getAllFoodsByCategory(categoryId: Long): Maybe<List<FoodModel>> {
        return foodCategoryDao.getAllFoodsByCategory(categoryId).map { relationItem ->
            relationItem.foods.map {
                foodEntityModelMapper.mapFromEntity(it, relationItem.fooDCategory.name)
            }
        }
    }

}

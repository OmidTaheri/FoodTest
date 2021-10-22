package ir.omidtaheri.foodtest.data.local.datasource

import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.local.dao.FoodDao
import ir.omidtaheri.foodtest.data.local.entity.FoodEntity
import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodDetailTuple
import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodTuple
import ir.omidtaheri.foodtest.data.mapper.*
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import ir.omidtaheri.foodtest.data.models.FoodModel
import javax.inject.Inject
import javax.inject.Named


class FoodLocalDataSourceImpl @Inject constructor(
    private val foodDao: FoodDao,
    @Named("FoodDetailTupleModelMapper")
    private val foodDetailTupleModelMapper: IEntityModelMapper<FoodDetailTuple, FoodDetailModel>,
    @Named("FoodTupleModelMapper")
    private val foodTupleModelMapper:  IEntityModelMapper<FoodTuple, FoodModel>,
    @Named("FoodDetailModelEntityMapper")
    private val foodDetailModelEntityMapper: IModelEntityMapper<FoodDetailModel, FoodEntity>,
) : IFoodLocalDataSource {

    override fun insertFood(food: FoodDetailModel): Single<Long> {
        return foodDao.insertFood(foodDetailModelEntityMapper.mapToEntity(food))
    }

    override fun getFoodDetail(foodId: Long): Single<FoodDetailModel> {
        return foodDao.getFoodDetail(foodId).map {
            foodDetailTupleModelMapper.mapFromEntity(it)
        }
    }

    override fun searchFood(query: String): Single<List<FoodModel>> {
        return foodDao.searchFood(query).map { list ->
            list.map {
                foodTupleModelMapper.mapFromEntity(it)
            }
        }
    }

}

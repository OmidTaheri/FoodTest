package ir.omidtaheri.foodtest.data.repository

import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.datastate.DataState
import ir.omidtaheri.foodtest.data.local.datasource.IFoodLocalDataSource
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import ir.omidtaheri.foodtest.data.models.FoodModel
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodLocalDataSource: IFoodLocalDataSource
) : IFoodRepository {
    override fun insertFood(food: FoodDetailModel): Single<DataState<Long>> {
        return foodLocalDataSource.insertFood(food).map<DataState<Long>> {
            DataState.Success(it)
        }.onErrorReturn {
            DataState.Error(it.message ?: "Insert Food Error")
        }
    }


    override fun getFoodDetail(foodId: Long): Single<DataState<FoodDetailModel>> {
        return foodLocalDataSource.getFoodDetail(foodId)
            .map<DataState<FoodDetailModel>> {
                DataState.Success(it)
            }.onErrorReturn {
                DataState.Error(it.message ?: "Get Food Detail Error")
            }
    }

    override fun searchFood(query: String): Single<DataState<List<FoodModel>>> {
        return foodLocalDataSource.searchFood(query)
            .map<DataState<List<FoodModel>>> {
                DataState.Success(it)
            }.onErrorReturn {
                DataState.Error(it.message ?: "Search Food Error")
            }
    }
}
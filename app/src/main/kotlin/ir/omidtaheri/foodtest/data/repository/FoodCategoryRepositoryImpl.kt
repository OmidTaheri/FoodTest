package ir.omidtaheri.foodtest.data.repository

import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.datastate.DataState
import ir.omidtaheri.foodtest.data.local.datasource.IFoodCategoryLocalDataSource
import ir.omidtaheri.foodtest.data.models.FoodCategoryModel
import ir.omidtaheri.foodtest.data.models.FoodModel
import javax.inject.Inject

class FoodCategoryRepositoryImpl @Inject constructor(
    private val foodCategoryLocalDataSource: IFoodCategoryLocalDataSource
) : IFoodCategoryRepository {
    override fun insertFoodCategory(foodCategory: FoodCategoryModel): Single<DataState<Long>> {
        return foodCategoryLocalDataSource.insertFoodCategory(foodCategory).map<DataState<Long>> {
            DataState.Success(it)
        }.onErrorReturn {
            DataState.Error(it.message ?: "Insert Food Category Error")
        }
    }

    override fun getAllFoodCategories(): Single<DataState<List<FoodCategoryModel>>> {
        return foodCategoryLocalDataSource.getAllFoodCategories()
            .map<DataState<List<FoodCategoryModel>>> {
                DataState.Success(it)
            }.onErrorReturn {
                DataState.Error(it.message ?: "Get Food Categories Error")
            }
    }

    override fun getAllFoodsByCategory(categoryId: Long): Single<DataState<List<FoodModel>>> {
        return foodCategoryLocalDataSource.getAllFoodsByCategory(categoryId)
            .map<DataState<List<FoodModel>>> {
                DataState.Success(it)
            }.onErrorReturn {
                DataState.Error(it.message ?: "Get FoodList Error")
            }
    }
}
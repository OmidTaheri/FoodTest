package ir.omidtaheri.foodtest.data.local.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.local.entity.FoodCategoryEntity
import ir.omidtaheri.foodtest.data.local.entity.relation.FoodAndCategory

@Dao
interface FoodCategoryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertFoodCategory(foodCategory: FoodCategoryEntity): Single<Long>

    @Query("SELECT * FROM FoodCategory")
    fun getAllFoodCategories(): Single<List<FoodCategoryEntity>>

    @Transaction
    @Query("SELECT * FROM FoodCategory WHERE cid = :categoryId")
    fun getAllFoodsByCategory(categoryId: Long): Single<FoodAndCategory>
}

package ir.omidtaheri.foodtest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.local.entity.FoodCategoryEntity
import ir.omidtaheri.foodtest.data.local.entity.relation.FoodAndCategory

@Dao
interface FoodCategoryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertFoodCategory(foodCategory: FoodCategoryEntity): Single<Long>

    @Query("SELECT * FROM FoodCategory")
    fun getAllFoodCategories(): Single<List<FoodCategoryEntity>>

    @Query("SELECT * FROM FoodCategory WHERE cid = :categoryId")
    fun getAllFoodsByCategory(categoryId: Long): Single<FoodAndCategory>
}

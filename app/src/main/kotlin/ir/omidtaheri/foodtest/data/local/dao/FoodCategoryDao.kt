package ir.omidtaheri.foodtest.data.local.dao

import androidx.annotation.Keep
import androidx.room.*
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.local.entity.FoodCategoryEntity
import ir.omidtaheri.foodtest.data.local.entity.relation.FoodAndCategory

@Keep
@Dao
interface FoodCategoryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertFoodCategory(foodCategory: FoodCategoryEntity): Single<Long>

    @Query("SELECT * FROM FoodCategory")
    fun getAllFoodCategories(): Maybe<List<FoodCategoryEntity>>

    @Transaction
    @Query("SELECT * FROM FoodCategory WHERE cid = :categoryId")
    fun getAllFoodsByCategory(categoryId: Long): Maybe<FoodAndCategory>
}

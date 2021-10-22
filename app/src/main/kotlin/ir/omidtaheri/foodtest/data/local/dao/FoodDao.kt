package ir.omidtaheri.foodtest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.local.entity.FoodEntity
import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodDetailTuple
import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodTuple

@Dao
interface FoodDao {

    @Insert
    fun insertFood(food: FoodEntity): Single<Long>

    @Query("SELECT * FROM Foods INNER JOIN FoodCategory ON  Foods.categoryId = FoodCategory.cid WHERE Foods.fid = :foodId")
    fun getFoodDetail(foodId: Long): Single<FoodDetailTuple>

    @Query("SELECT title,Foods.fid,FoodCategory.name As categoryName,imageUrl FROM Foods INNER JOIN FoodCategory ON Foods.categoryId = FoodCategory.cid WHERE title LIKE :query OR materials LIKE :query")
    fun searchFood(query: String): Single<List<FoodTuple>>

}
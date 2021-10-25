package ir.omidtaheri.foodtest.data.local.dao

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ir.omidtaheri.foodtest.data.local.entity.FoodEntity
import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodDetailTuple
import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodTuple

@Keep
@Dao
interface FoodDao {

    @Insert
    fun insertFood(food: FoodEntity): Single<Long>

    @Query("SELECT title,fid,name,cid,Foods.imageUrl,recipe,materials FROM Foods INNER JOIN FoodCategory ON  Foods.categoryId = FoodCategory.cid WHERE Foods.fid = :foodId")
    fun getFoodDetail(foodId: Long): Maybe<FoodDetailTuple>

    @Query("SELECT title,Foods.fid,FoodCategory.name As categoryName,Foods.imageUrl As imageUrl FROM Foods INNER JOIN FoodCategory ON Foods.categoryId = FoodCategory.cid WHERE title LIKE :query OR materials LIKE :query")
    fun searchFood(query: String): Maybe<List<FoodTuple>>

}

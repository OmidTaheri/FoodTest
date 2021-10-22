package ir.omidtaheri.foodtest.data.local.entity.tuple

import androidx.room.ColumnInfo


data class FoodDetailTuple(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "fid")
    val foodId: Long,
    @ColumnInfo(name = "name")
    val categoryName: String,
    @ColumnInfo(name = "cid")
    val categoryId: Long,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "recipe")
    val recipe: String,
    @ColumnInfo(name = "materials")
    val materials: String
)

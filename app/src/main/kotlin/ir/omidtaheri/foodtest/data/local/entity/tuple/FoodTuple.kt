package ir.omidtaheri.foodtest.data.local.entity.tuple

import androidx.room.ColumnInfo


data class FoodTuple(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "fid")
    val id: Long,
    @ColumnInfo(name = "categoryName")
    val categoryName: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
)

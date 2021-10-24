package ir.omidtaheri.foodtest.data.local.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "Foods")
data class FoodEntity(
    @ColumnInfo(name = "title")
    val title: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fid")
    val id: Long,
    @ColumnInfo(name = "categoryId")
    val categoryId: Long,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "recipe")
    val recipe: String,
    @ColumnInfo(name = "materials")
    val materials: String
)

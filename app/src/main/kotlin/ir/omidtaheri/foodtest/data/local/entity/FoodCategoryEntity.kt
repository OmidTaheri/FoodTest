package ir.omidtaheri.foodtest.data.local.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Keep
@Entity(
    tableName = "FoodCategory", indices = [Index(
        value = ["name"],
        unique = true
    )]
)
data class FoodCategoryEntity(

    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cid")
    val id: Long,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
)

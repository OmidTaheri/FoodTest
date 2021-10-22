package ir.omidtaheri.foodtest.data.local.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import ir.omidtaheri.foodtest.data.local.entity.FoodCategoryEntity
import ir.omidtaheri.foodtest.data.local.entity.FoodEntity

data class FoodAndCategory(
    @Embedded val fooDCategory: FoodCategoryEntity,
    @Relation(
        parentColumn = "cid",
        entityColumn = "categoryId"
    )
    val foods: List<FoodEntity>
)

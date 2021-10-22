package ir.omidtaheri.foodtest.data.models

data class FoodDetailModel(
    val title: String,
    val id: Long,
    val categoryName: String,
    val categoryId: Long,
    val imageUrl: String?,
    val recipe: String,
    val materials: String
)

package ir.omidtaheri.foodtest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.omidtaheri.foodtest.data.local.dao.FoodCategoryDao
import ir.omidtaheri.foodtest.data.local.dao.FoodDao
import ir.omidtaheri.foodtest.data.local.entity.FoodCategoryEntity
import ir.omidtaheri.foodtest.data.local.entity.FoodEntity

@Database(entities = [FoodEntity::class, FoodCategoryEntity::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDao
    abstract fun getFoodCategoryDao(): FoodCategoryDao
}

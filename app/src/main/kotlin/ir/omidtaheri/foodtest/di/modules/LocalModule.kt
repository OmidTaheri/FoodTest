package ir.omidtaheri.foodtest.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.omidtaheri.foodtest.data.local.dao.FoodCategoryDao
import ir.omidtaheri.foodtest.data.local.dao.FoodDao
import ir.omidtaheri.foodtest.data.local.database.FoodDatabase
import javax.inject.Named
import javax.inject.Singleton

@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context, @Named("dbName") dbName: String): FoodDatabase {
        return Room.databaseBuilder(
            context,
            FoodDatabase::class.java,
            dbName
        ).createFromAsset("food.db").build()
    }

    @Singleton
    @Provides
    fun provideFoodDao(database: FoodDatabase): FoodDao {
        return database.getFoodDao()
    }

    @Singleton
    @Provides
    fun provideFoodCategoryDao(database: FoodDatabase): FoodCategoryDao {
        return database.getFoodCategoryDao()
    }
}

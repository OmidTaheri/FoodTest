package ir.omidtaheri.foodtest.di.modules

import dagger.Binds
import dagger.Module
import ir.omidtaheri.foodtest.data.local.entity.FoodCategoryEntity
import ir.omidtaheri.foodtest.data.local.entity.FoodEntity
import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodDetailTuple
import ir.omidtaheri.foodtest.data.local.entity.tuple.FoodTuple
import ir.omidtaheri.foodtest.data.mapper.*
import ir.omidtaheri.foodtest.data.models.FoodCategoryModel
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import ir.omidtaheri.foodtest.data.models.FoodModel
import javax.inject.Named
import javax.inject.Singleton

@Module
interface MapperModule {


    @Singleton
    @Binds
    @Named("FoodCategoryModelEntityMapper")
    fun provideFoodCategoryModelEntityMapper(
        mapper: FoodCategoryModelEntityMapper
    ): IModelEntityMapper<FoodCategoryModel, FoodCategoryEntity>

    @Singleton
    @Binds
    @Named("FoodCategoryEntityModelMapper")
    fun provideFoodCategoryEntityModelMapper(
        mapper: FoodCategoryModelEntityMapper
    ): IEntityModelMapper<FoodCategoryEntity, FoodCategoryModel>


    @Singleton
    @Binds
    @Named("FoodEntityModelMapper")
    fun provideFoodEntityModelMapper(
        mapper: FoodModelEntityMapper
    ): IEntityModelByParamMapper<FoodEntity, FoodModel, String>


    @Singleton
    @Binds
    @Named("FoodDetailTupleModelMapper")
    fun provideFoodDetailTupleModelMapper(
        mapper: FoodDetailModelTupleMapper
    ): IEntityModelMapper<FoodDetailTuple, FoodDetailModel>


    @Singleton
    @Binds
    @Named("FoodTupleModelMapper")
    fun provideFoodTupleModelMapper(
        mapper: FoodModelTupleMapper
    ): IEntityModelMapper<FoodTuple, FoodModel>


    @Singleton
    @Binds
    @Named("FoodDetailModelEntityMapper")
    fun provideFoodDetailModelEntityMapper(
        mapper: FoodDetailModelEntityMapper
    ): IModelEntityMapper<FoodDetailModel, FoodEntity>

}

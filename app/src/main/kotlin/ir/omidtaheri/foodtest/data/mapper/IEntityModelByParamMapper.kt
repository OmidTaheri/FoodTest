package ir.omidtaheri.foodtest.data.mapper

interface IEntityModelByParamMapper<Entity, Model, ParamType> {
    fun mapFromEntity(from: Entity, param: ParamType): Model
}

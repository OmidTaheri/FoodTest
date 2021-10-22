package ir.omidtaheri.foodtest.data.mapper

interface IEntityModelMapper<Entity, Model> {
    fun mapFromEntity(from: Entity): Model
}

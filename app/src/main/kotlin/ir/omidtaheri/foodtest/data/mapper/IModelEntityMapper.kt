package ir.omidtaheri.foodtest.data.mapper

interface IModelEntityMapper<Model,Entity> {
    fun mapToEntity(from: Model): Entity
}

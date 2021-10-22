package ir.omidtaheri.foodtest.data.datastate

sealed class DataState<T> {

    class Success<T>(val data: T?, val message: String? = null) : DataState<T>()

    class Error<T>(val errorMessage: String? = null) : DataState<T>()
}

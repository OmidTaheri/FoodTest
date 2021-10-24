package ir.omidtaheri.foodtest.presentation.categorylist.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import io.reactivex.rxjava3.kotlin.subscribeBy
import ir.omidtaheri.foodtest.base.BaseAndroidViewModel
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.data.datastate.DataState
import ir.omidtaheri.foodtest.data.models.FoodCategoryModel
import ir.omidtaheri.foodtest.data.repository.IFoodCategoryRepository

class CategoryViewModel(
    private val foodCategoryRepository: IFoodCategoryRepository,
    private val rxSchedulers: BaseSchedulers,
    private val state: SavedStateHandle,
    private val mApplication: Application
) :
    BaseAndroidViewModel(mApplication, state) {

    private val _categoryItems: MutableLiveData<List<FoodCategoryModel>> = MutableLiveData()
    val categoryItems: LiveData<List<FoodCategoryModel>>
        get() = _categoryItems


    private val _errorState: MutableLiveData<String> = MutableLiveData()
    val errorState: LiveData<String>
        get() = _errorState


    fun getCategoryList() {
        val disposable = foodCategoryRepository.getAllFoodCategories()
            .subscribeOn(rxSchedulers.subscribeOn)
            .observeOn(rxSchedulers.observeOn)
            .subscribeBy { response ->
                when (response) {

                    is DataState.Success -> {
                        response.data?.let {
                            _categoryItems.value = it
                        }
                    }

                    is DataState.Error -> {
                        response.errorMessage?.let {
                            handleError(it)
                        }
                    }
                }
            }

        addDisposable(disposable)
    }


    private fun handleError(errorMessage: String) {
        _errorState.value = errorMessage
    }
}

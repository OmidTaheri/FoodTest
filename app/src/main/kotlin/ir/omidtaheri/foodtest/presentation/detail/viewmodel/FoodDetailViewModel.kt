package ir.omidtaheri.foodtest.presentation.detail.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import io.reactivex.rxjava3.kotlin.subscribeBy
import ir.omidtaheri.foodtest.base.BaseAndroidViewModel
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.base.singlelivedata.SingleLiveData
import ir.omidtaheri.foodtest.data.datastate.DataState
import ir.omidtaheri.foodtest.data.models.FoodDetailModel
import ir.omidtaheri.foodtest.data.repository.IFoodRepository

class FoodDetailViewModel(
    private val foodCRepository: IFoodRepository,
    private val rxSchedulers: BaseSchedulers,
    private val state: SavedStateHandle,
    private val mApplication: Application
) :
    BaseAndroidViewModel(mApplication, state) {

    private val _foodDetail: MutableLiveData<FoodDetailModel> = MutableLiveData()
    val foodDetail: LiveData<FoodDetailModel>
        get() = _foodDetail

    private val _errorMessage: MutableLiveData<String> = SingleLiveData()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    fun getFoodDetail(foodId: Long) {
        val disposable =
            foodCRepository.getFoodDetail(foodId)
                .subscribeOn(rxSchedulers.subscribeOn)
                .observeOn(rxSchedulers.observeOn)
                .subscribeBy { response ->
                    when (response) {

                        is DataState.Success -> {
                            response.data?.let {
                                _foodDetail.value = it
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
        _errorMessage.value = errorMessage
    }
}

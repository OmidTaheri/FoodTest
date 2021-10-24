package ir.omidtaheri.foodtest.presentation.search.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import ir.omidtaheri.foodtest.base.BaseAndroidViewModel
import ir.omidtaheri.foodtest.base.BaseSchedulers
import ir.omidtaheri.foodtest.data.datastate.DataState
import ir.omidtaheri.foodtest.data.models.FoodModel
import ir.omidtaheri.foodtest.data.repository.IFoodRepository
import java.util.concurrent.TimeUnit

class SearchViewModel(
    private val foodRepository: IFoodRepository,
    private val rxSchedulers: BaseSchedulers,
    private val state: SavedStateHandle,
    private val mApplication: Application
) :
    BaseAndroidViewModel(mApplication, state) {

    val searchSubject: PublishSubject<String> = PublishSubject.create()

    private val _foods: MutableLiveData<List<FoodModel>> = MutableLiveData()
    val foods: LiveData<List<FoodModel>>
        get() = _foods

    private val _errorState: MutableLiveData<String> = MutableLiveData()
    val errorState: LiveData<String>
        get() = _errorState


    fun searchQuery(query: String) {
        val disposable =
            foodRepository.searchFood(query)
                .subscribeOn(rxSchedulers.subscribeOn)
                .observeOn(rxSchedulers.observeOn)
                .subscribeBy { response ->
                    when (response) {

                        is DataState.Success -> {
                            response.data?.let {
                                _foods.value = it
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


    fun setSearchSubjectObserver() {
        val disposable = searchSubject.debounce(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(rxSchedulers.subscribeOn)
            .switchMapSingle {
                _isLoading.postValue(true)
                foodRepository.searchFood(it)
            }
            .observeOn(rxSchedulers.observeOn)
            .subscribeBy { response ->
                when (response) {

                    is DataState.Success -> {
                        response.data?.let {
                            _foods.value = it
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

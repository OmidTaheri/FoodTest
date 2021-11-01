package ir.omidtaheri.foodtest.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import ir.omidtaheri.foodtest.base.singlelivedata.SingleLiveData

open class BaseAndroidViewModel(
    private val mApplication: Application,
    private val state: SavedStateHandle
) : AndroidViewModel(mApplication) {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected val _isLoading: MutableLiveData<Boolean> = SingleLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun addDisposable(disposable: Disposable) {

        compositeDisposable.add(disposable)
    }

    fun removeDisposable(disposable: Disposable) {

        compositeDisposable.delete(disposable)
    }


    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }
}

package ir.omidtaheri.foodtest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _toolbarTitle: MutableLiveData<String> = MutableLiveData()
    val toolbarTitle: LiveData<String>
        get() = _toolbarTitle

    fun setToolbarTitle(title: String) {
        _toolbarTitle.value = title
    }
}

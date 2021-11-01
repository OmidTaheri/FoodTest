package ir.omidtaheri.foodtest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import ir.omidtaheri.foodtest.base.viewmodelutils.ViewModelAssistedFactory
import javax.inject.Inject

abstract class BaseFragment<T : ViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelAssistedFactory<T>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateViewBinding(inflater, container)
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): View?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
        configDaggerComponent()
    }

    abstract fun bindUi()

    abstract fun setLivaDataObservers()

    abstract fun configDaggerComponent()

}

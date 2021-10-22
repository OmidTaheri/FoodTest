package ir.omidtaheri.foodtest.di.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ir.omidtaheri.foodtest.base.BaseSchedulers
import javax.inject.Inject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class AppScheduler @Inject constructor() : BaseSchedulers {
    override val subscribeOn: Scheduler
        get() = Schedulers.io()
    override val observeOn: Scheduler
        get() = AndroidSchedulers.mainThread()
}

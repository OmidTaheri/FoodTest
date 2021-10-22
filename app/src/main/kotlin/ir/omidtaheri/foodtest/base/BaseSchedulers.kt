package ir.omidtaheri.foodtest.base

import io.reactivex.rxjava3.core.Scheduler

interface BaseSchedulers {
    val subscribeOn: Scheduler
    val observeOn: Scheduler
}

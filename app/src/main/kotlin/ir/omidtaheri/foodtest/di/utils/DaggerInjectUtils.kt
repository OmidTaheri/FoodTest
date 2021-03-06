package ir.omidtaheri.foodtest.di.utils

import android.content.Context
import ir.omidtaheri.foodtest.di.ApplicationComponentProvider
import ir.omidtaheri.foodtest.di.components.ApplicationComponent

object DaggerInjectUtils {

    fun provideApplicationComponent(applicationContext: Context): ApplicationComponent {

        return if (applicationContext is ApplicationComponentProvider) {
            applicationContext.provideApplicationComponent()
        } else {
            throw IllegalStateException("Provide the application context which implement ApplicationComponentProvider")
        }
    }
}

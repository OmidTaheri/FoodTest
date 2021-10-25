package ir.omidtaheri.foodtest

import android.app.Application
import android.content.Context
import ir.omidtaheri.foodtest.di.ApplicationComponentProvider
import ir.omidtaheri.foodtest.di.components.ApplicationComponent
import ir.omidtaheri.foodtest.di.components.DaggerApplicationComponent
import ir.omidtaheri.foodtest.utils.LocaleHelper

class ApplicationClass : Application(), ApplicationComponentProvider {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        this.applicationComponent =
            DaggerApplicationComponent.factory()
                .create(this, "FoodDatabase.db")

        applicationComponent.inject(this)

    }

    override fun provideApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.setLocale(base, LocaleHelper.Persian))
    }

}

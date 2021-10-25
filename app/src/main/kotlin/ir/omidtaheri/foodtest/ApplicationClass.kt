package ir.omidtaheri.foodtest

import android.app.Application
import ir.omidtaheri.foodtest.di.ApplicationComponentProvider
import ir.omidtaheri.foodtest.di.components.ApplicationComponent
import ir.omidtaheri.foodtest.di.components.DaggerApplicationComponent

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


}

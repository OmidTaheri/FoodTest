package ir.omidtaheri.foodtest.utils

import android.app.Application
import android.content.Context
import android.os.Build
import java.util.*

object LocaleHelper {

    val Persian: Locale by lazy { Locale("fa", "IR") }

    /**
     * Returns the system [Locale]
     */
    val systemLocale: Locale = Locale.getDefault()

    /**
     * Sets [locale] for [context]
     */
    fun setLocale(context: Context?, locale: Locale): Context? {
        Locale.setDefault(locale)
        return updateContextResources(context, locale)
    }


    private fun updateContextResources(context: Context?, locale: Locale): Context? {
        context?.let {
            if (context.currentLocale == locale && context is Application) {
                return context
            }

            val resources = context.resources
            val configuration = resources.configuration
            configuration.setCurrentLocale(locale)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLayoutDirection(locale)
            }

            return if (Build.VERSION.SDK_INT >= 17) {
                context.createConfigurationContext(configuration)
            } else {
                @Suppress("DEPRECATION")
                resources.updateConfiguration(configuration, resources.displayMetrics)
                context
            }
        }

        return context
    }

}
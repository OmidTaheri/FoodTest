package ir.omidtaheri.foodtest.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ir.omidtaheri.foodtest.utils.LocaleHelper


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(inflateViewBinding(layoutInflater))
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater): View?

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.setLocale(base, LocaleHelper.Persian))
    }
}

package ir.omidtaheri.foodtest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(inflateViewBinding(layoutInflater))
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater): View?
}

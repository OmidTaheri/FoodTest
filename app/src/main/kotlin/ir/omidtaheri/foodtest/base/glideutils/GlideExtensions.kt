package ir.omidtaheri.foodtest.base.glideutils

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ir.omidtaheri.foodtest.R

fun ImageView.loadFoodImage(url: String, mConetxt: Context) {

    val requestOptions = RequestOptions()
    requestOptions.apply {
        placeholder(R.drawable.food_placeholder)
    }
    GlideApp.with(mConetxt)
        .load(url)
        .skipMemoryCache(false)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(160, 160)
        .thumbnail(0.4f)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.loadFoodCategoryImage(url: String, mConetxt: Context) {
    val requestOptions = RequestOptions()
    requestOptions.apply {
        placeholder(R.drawable.category_food_placeholder)
    }
    GlideApp.with(mConetxt)
        .load(url)
        .skipMemoryCache(false)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(320, 80)
        .thumbnail(0.6f)
        .apply(requestOptions)
        .into(this)
}


fun ImageView.clear(myConetxt: Context) {
    GlideApp.with(myConetxt)
        .clear(this)
}


fun Fragment.onDestroyGlide() {
    GlideApp.get(requireContext()).clearMemory()
}

package com.ellfors.mvvmtest.bindadp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ellfors.mvvmtest.R

@BindingAdapter("loadImg")
fun ImageView.loadImg(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .centerInside()
            .apply(RequestOptions.errorOf(R.drawable.ic_common_empty))
            .into(this)
    }
}
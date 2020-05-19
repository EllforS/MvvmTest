package com.ellfors.mvvmtest.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImg")
fun loadImg(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
            .load(url)
            .centerInside()
            .into(view)
    }
}
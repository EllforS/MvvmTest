package com.ellfors.mvvmtest.bindadp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImg")
fun ImageView.loadImg(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .centerInside()
            .into(this)
    }
}
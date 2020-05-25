package com.ellfors.mvvmtest.bindadp

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

/**
 * EditTextBindingAdapter
 * 2020-05-25 14:35
 */
@BindingAdapter("onTextChanged")
fun EditText.onTextChanged(text: MutableLiveData<String>) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(value: Editable?) {
            text.value = value?.toString() ?: ""
        }

        override fun beforeTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    })
}
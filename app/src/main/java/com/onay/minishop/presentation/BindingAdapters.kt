package com.onay.minishop.presentation

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onay.minishop.R


@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textView: TextInputLayout , boolean: Boolean){
    textView.error = if (boolean) {
            textView.context.getString(R.string.error_input_count)
        } else {
            null
        }
}
@BindingAdapter("errorInputName")
fun bindErrorInputName(textView: TextInputLayout , boolean: Boolean){
    textView.error = if(boolean){
        textView.context.getString(R.string.error_input_name)
    }
    else{
        null
    }
}
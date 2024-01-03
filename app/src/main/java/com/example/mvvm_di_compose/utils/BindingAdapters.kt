package com.example.mvvm_di_compose.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * to set image from network
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

/**
 * use to set 200 character string text to text view
 */
@BindingAdapter("textMaxSize200")
fun cropText(view: TextView, text: String?) {
    if (!text.isNullOrEmpty()) {
        view.setText(text.take(200))
    }
}


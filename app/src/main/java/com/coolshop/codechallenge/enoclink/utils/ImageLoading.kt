package com.coolshop.codechallenge.enoclink.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide


fun ImageView.load(context: Context, bitmap: Bitmap) {
    Glide.with(context)
            .load(bitmap)
            .into(this)
}

fun ImageView.load(context: Context, url: String?, @DrawableRes placeholder: Int) {
    Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .into(this)
}
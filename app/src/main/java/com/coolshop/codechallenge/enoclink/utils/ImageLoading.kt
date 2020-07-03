package com.coolshop.codechallenge.enoclink.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun loadImage(context: Context, url: String?, imageView: ImageView) {
    Glide.with(context)
            .load(url)
            .into(imageView)
}

fun loadImage(context: Context, bitmap: Bitmap, imageView: ImageView) {
    Glide.with(context)
            .load(bitmap)
            .into(imageView)
}

fun loadImage(context: Context, url: String?, imageView: ImageView, @DrawableRes placeholder: Int) {
    Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .into(imageView)
}

fun getGravatarUrl(email: String) : String {
    val hash = email.md5()
    return "http://www.gravatar.com/avatar/$hash?s=100&d=404"
}
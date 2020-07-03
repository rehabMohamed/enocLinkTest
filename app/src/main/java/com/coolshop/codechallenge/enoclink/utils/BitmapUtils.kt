package com.coolshop.codechallenge.enoclink.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.Base64
import java.io.ByteArrayOutputStream

fun Bitmap.applyGrayScaleFilter(): Bitmap {
    val bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmpGrayscale)
    val paint = Paint()
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0f)
    val colorMatrixFilter = ColorMatrixColorFilter(colorMatrix)
    paint.colorFilter = colorMatrixFilter
    canvas.drawBitmap(this, 0f, 0f, paint)
    return bmpGrayscale
}

fun Bitmap.encodeImage() : String {
    val baos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, baos)

    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
}
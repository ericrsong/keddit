@file:JvmName("ExtensionsUtils")

package com.example.redditapp.commons

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.redditapp.R
import com.squareup.picasso.Picasso

// This extends the methods for ViewGroup class. This is called extension.

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context)
                        .inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImage(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.get().load(R.mipmap.ic_launcher).into(this)
        Log.e("LoadImage", "Success")
    } else {
        Picasso.get().load(imageUrl).into(this)
        Log.e("LoadImage", "Failure")
    }

}
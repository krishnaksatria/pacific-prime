package com.id.pacificprime.commons.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.id.pacificprime.commons.R

fun ImageView.loadImage(url: String) {
    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.image_progress_animation)
        .error(R.drawable.image_square_placeholder)
        .into(this)
}

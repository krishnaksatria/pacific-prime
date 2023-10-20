package com.id.pacificprime.commons.ui.loading

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.id.pacificprime.commons.R
import com.id.pacificprime.commons.databinding.ViewCustomLoadingBinding

class CustomLoadingView(
    context: Context,
    attrs: AttributeSet?
) : RelativeLayout(context, attrs) {

    private val binding: ViewCustomLoadingBinding = ViewCustomLoadingBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var overlayBackground: Drawable
        get() = binding.layoutOverlay.background
        set(value) {
            binding.layoutOverlay.background = value
        }

    var progressBackground: Drawable
        get() = binding.progressBarLoading.background
        set(value) {
            binding.progressBarLoading.background = value
        }

    fun setProgressTint(value: Int) {
        binding.progressBarLoading.indeterminateDrawable.colorFilter =
            PorterDuffColorFilter(value, PorterDuff.Mode.MULTIPLY)
    }

    init {
        if (attrs != null) {
            val attributes =
                context.obtainStyledAttributes(attrs, R.styleable.CustomLoadingView)
            try {
                //OverlayBackground
                val overlayDrawable =
                    attributes.getDrawable(R.styleable.CustomLoadingView_overlayBackground)
                overlayDrawable?.let {
                    overlayBackground = it
                }

                //ProgressBackground
                val progressDrawable =
                    attributes.getDrawable(R.styleable.CustomLoadingView_progressBackground)
                progressDrawable?.let {
                    progressBackground = it
                }

                //ProgressTint
                val progressTint = attributes.getColor(
                    R.styleable.CustomLoadingView_progressTint,
                    ContextCompat.getColor(context, R.color.secondary_green)
                )
                setProgressTint(progressTint)
            } finally {
                attributes.recycle()
            }
        }
    }
}

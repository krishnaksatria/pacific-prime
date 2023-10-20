package com.id.pacificprime.commons.ui.appbar

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.id.pacificprime.commons.R
import com.id.pacificprime.commons.databinding.LayoutNavigationAppBarBinding

class NavigationAppBar(
    context: Context,
    attrs: AttributeSet?
) : RelativeLayout(context, attrs) {

    private val binding: LayoutNavigationAppBarBinding = LayoutNavigationAppBarBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var title: CharSequence
        get() = binding.textViewTitle.text
        set(value) {
            binding.textViewTitle.text = value
        }

    var endButtonSrc: Int = 0
        set(value) {
            field = value
            binding.buttonEnd.setCompoundDrawablesWithIntrinsicBounds(
                value,
                0,
                0,
                0
            )
        }

    private var endButtonClickListener: OnClickListener? = null

    init {
        if (attrs != null) {
            val attributes =
                context.obtainStyledAttributes(attrs, R.styleable.NavigationAppBar)
            try {
                //Title
                title = attributes.getString(R.styleable.NavigationAppBar_title) ?: ""

                //EndButtonSrc
                endButtonSrc = attributes.getResourceId(
                    R.styleable.NavigationAppBar_endButtonSrc,
                    0
                )
            } finally {
                attributes.recycle()
            }
        }

        binding.imageButtonBack.setOnClickListener {
            if (context is Activity) {
                context.finish()
            }
        }

        binding.buttonEnd.setOnClickListener {
            endButtonClickListener?.onClick(it)
        }
    }

    fun setOnEndButtonClickListener(listener: OnClickListener) {
        endButtonClickListener = listener
    }
}

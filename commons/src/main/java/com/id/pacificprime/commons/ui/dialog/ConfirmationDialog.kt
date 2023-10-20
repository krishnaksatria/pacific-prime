package com.id.pacificprime.commons.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import com.id.pacificprime.commons.R
import com.id.pacificprime.commons.databinding.DialogConfirmationBinding
import com.id.pacificprime.libraries.extension.setGone

class ConfirmationDialog(context: Context) :
    Dialog(context, android.R.style.Theme_Material_Light_Dialog_NoActionBar) {

    private val binding = DialogConfirmationBinding.inflate(layoutInflater, null, false)

    override fun setTitle(title: CharSequence?) {
        binding.textConfirmationTitle.text = title
    }

    var icon: Drawable? = null
        set(value) {
            field = value
            if (value != null) {
                binding.imageConfirmation.setImageDrawable(value)
            }
        }

    var description: String? = null
        set(value) {
            field = value
            if (value != null && value.isNotEmpty()) {
                binding.textConfirmationDesc.text = value
            } else {
                binding.textConfirmationDesc.setGone()
            }
        }

    init {
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        val dialogSize = context.resources.getDimensionPixelSize(R.dimen.dimens_250dp)
        window?.apply {
            setLayout(
                dialogSize,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}

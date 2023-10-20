package com.id.pacificprime.commons.extensions

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.id.pacificprime.commons.R
import com.id.pacificprime.commons.ui.dialog.ConfirmationDialog

fun Context.showError(message: String? = null) {
    val errorDialog = ConfirmationDialog(this)
    errorDialog.apply {
        setTitle(R.string.label_error_general_title)
        description = message ?: getString(R.string.label_error_general_desc)
        icon = ContextCompat.getDrawable(
            context,
            R.drawable.ic_error
        )
        setCanceledOnTouchOutside(true)
    }

    errorDialog.show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

package com.id.pacificprime.commons.navigation

import android.content.Context
import android.content.Intent

interface MovieDetailNavigation {
    fun createIntent(context: Context?, movie: Int): Intent
}

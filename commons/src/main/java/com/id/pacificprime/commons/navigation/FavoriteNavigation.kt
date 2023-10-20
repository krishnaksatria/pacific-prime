package com.id.pacificprime.commons.navigation

import android.content.Context
import android.content.Intent

interface FavoriteNavigation {
    fun createIntent(context: Context?): Intent
}

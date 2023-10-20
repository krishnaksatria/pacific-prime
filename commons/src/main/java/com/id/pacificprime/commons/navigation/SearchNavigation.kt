package com.id.pacificprime.commons.navigation

import android.content.Context
import android.content.Intent

interface SearchNavigation {
    fun createIntent(context: Context?, keyword: String): Intent
}

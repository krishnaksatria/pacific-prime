package com.id.pacificprime.core.preferences.domain

interface PreferenceRepositoryContract {
    fun setToken(token: String)
    fun getToken(): String
}

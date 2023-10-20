package com.id.pacificprime.core.preferences.data

import com.id.pacificprime.core.preferences.domain.PreferenceRepositoryContract
import com.id.pacificprime.libraries.encrypted.EncryptedPreferencesContract

class PreferenceRepository(
    private val encryptedPreferencesContract: EncryptedPreferencesContract
) : PreferenceRepositoryContract {
    override fun setToken(token: String) {
        encryptedPreferencesContract.setString(TOKEN, token)
    }

    override fun getToken(): String =
        encryptedPreferencesContract.getString(TOKEN) ?: ""

    companion object {
        const val TOKEN = "token"
    }
}

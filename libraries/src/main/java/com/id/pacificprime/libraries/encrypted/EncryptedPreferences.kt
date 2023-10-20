package com.id.pacificprime.libraries.encrypted

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class EncryptedPreferences(
    context: Context,
    securePreferencesFileKey: String
) : EncryptedPreferencesContract {

    private val sharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        //If allowBackup is set to true and the user uninstall & reinstall the app,
        //It will throw exception because the generated masterKey is different
        EncryptedSharedPreferences.create(
           securePreferencesFileKey,
           masterKeyAlias,
           context,
           EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
           EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } else {
        context.getSharedPreferences(securePreferencesFileKey, Context.MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun setInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.commit()
    }

    override fun setLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.commit()
    }

    override fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    override fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    override fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun getString(key: String, defaultValue: String): String {
        return getString(key) ?: defaultValue
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun getLong(key: String, defaultValue: Int): Long {
        return sharedPreferences.getLong(key, defaultValue.toLong())
    }

    override fun remove(key: String) {
        editor.remove(key)
        editor.commit()
    }

    override fun clear() {
        editor.clear()
        editor.commit()
    }
}

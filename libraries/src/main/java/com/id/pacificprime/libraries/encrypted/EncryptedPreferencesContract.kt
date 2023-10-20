package com.id.pacificprime.libraries.encrypted

interface EncryptedPreferencesContract {
    fun setInt(key: String, value: Int)
    fun setLong(key: String, value: Long)
    fun setString(key: String, value: String)
    fun setBoolean(key: String, value: Boolean)

    fun getString(key: String): String?
    fun getString(key: String, defaultValue: String): String
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun getInt(key: String, defaultValue: Int): Int
    fun getLong(key: String, defaultValue: Int): Long

    fun remove(key: String)
    fun clear()
}

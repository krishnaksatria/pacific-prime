package com.id.pacificprime.di

import com.id.pacificprime.libraries.encrypted.EncryptedPreferences
import com.id.pacificprime.libraries.encrypted.EncryptedPreferencesContract
import com.id.pacificprime.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

var libraryModule = module {
    single<EncryptedPreferencesContract> {
        EncryptedPreferences(
            androidContext(),
            SECURE_PREFS_FILE_KEY
        )
    }
}

const val SECURE_PREFS_FILE_KEY = "${BuildConfig.APPLICATION_ID}.secure_preferences"

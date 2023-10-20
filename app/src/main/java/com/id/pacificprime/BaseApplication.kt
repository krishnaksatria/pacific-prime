package com.id.pacificprime

import android.app.Application
import com.id.pacificprime.di.coreModule
import com.id.pacificprime.di.featureModule
import com.id.pacificprime.di.libraryModule
import com.id.pacificprime.di.navigationModule
import com.id.pacificprime.di.networkModule
import com.id.pacificprime.di.roomCoreModule
import com.id.pacificprime.di.roomRepositoryCoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() = startKoin {
        androidContext(this@BaseApplication)

        val modules = listOf(
            libraryModule,
            networkModule,
            coreModule,
            navigationModule,
            featureModule,
            roomRepositoryCoreModule,
            roomCoreModule
        )
        modules(modules)
    }

    private fun initTimber() {
        Timber.plant(TimberTreeLogging())
    }
}

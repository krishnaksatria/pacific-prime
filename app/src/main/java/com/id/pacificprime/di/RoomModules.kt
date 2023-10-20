package com.id.pacificprime.di

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.id.pacificprime.room.data.FavoriteMovieData
import com.id.pacificprime.room.data.RoomDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomCoreModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PacificPrimeDatabase::class.java,
            PacificPrimeDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}

@Database(
    entities = [
        FavoriteMovieData::class
    ],
    version = 1,
    exportSchema = false
)

abstract class PacificPrimeDatabase : RoomDatabase() {

    abstract fun roomDao(): RoomDao

    companion object {
        const val DATABASE_NAME = "pacificprime.db"
    }
}

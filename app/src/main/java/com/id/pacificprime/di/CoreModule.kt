package com.id.pacificprime.di

import com.id.pacificprime.core.movie.data.MovieRepository
import com.id.pacificprime.core.movie.data.MovieService
import com.id.pacificprime.core.movie.domain.MovieRepositoryContract
import com.id.pacificprime.core.preferences.data.PreferenceRepository
import com.id.pacificprime.core.preferences.domain.PreferenceRepositoryContract
import org.koin.dsl.module
import retrofit2.Retrofit

val coreModule = module {
    single<PreferenceRepositoryContract> {
        PreferenceRepository(
            encryptedPreferencesContract = get()
        )
    }
    single<MovieRepositoryContract> {
        val retrofit: Retrofit = get()
        MovieRepository(
            movieService = retrofit.create(MovieService::class.java)
        )
    }
}

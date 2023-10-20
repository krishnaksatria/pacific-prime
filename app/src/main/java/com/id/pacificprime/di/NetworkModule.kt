package com.id.pacificprime.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.id.pacificprime.BuildConfig
import com.id.pacificprime.core.preferences.domain.PreferenceRepositoryContract
import com.id.pacificprime.libraries.interceptor.AuthorizationHeaderInterceptor
import com.id.pacificprime.libraries.interceptor.NetworkConnectionInterceptor
import com.id.pacificprime.libraries.interceptor.UnauthorizedNetworkInterceptor
import java.util.concurrent.TimeUnit.SECONDS
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        initOkHttpClient(
            context = androidContext(),
            preferencesRepositoryContract = get()
        )
    }

    single {
        initRetrofit(
            okHttpClient = get()
        )
    }
}

fun initOkHttpClient(
    context: Context,
    preferencesRepositoryContract: PreferenceRepositoryContract,
): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(60, SECONDS)
        .connectTimeout(60, SECONDS)
        .addInterceptor(
            AuthorizationHeaderInterceptor { preferencesRepositoryContract.getToken() }
        )
        .addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context, true))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .addInterceptor(NetworkConnectionInterceptor(context))
        .addInterceptor(
            UnauthorizedNetworkInterceptor { _, _ ->
                if (preferencesRepositoryContract.getToken().isEmpty()) {
                    return@UnauthorizedNetworkInterceptor
                }
            }
        )
        .build()
}

fun initRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val gson = GsonBuilder().create()

    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}

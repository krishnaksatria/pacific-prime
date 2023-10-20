package com.id.pacificprime.libraries.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationHeaderInterceptor(
    private val getTokenListener: (AuthorizationHeaderInterceptor) -> String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getTokenListener.invoke(this)
        return if (token.isNotEmpty()) {
            val request = chain.request().newBuilder().addHeader(
                "Authorization", "Bearer $token"
            ).build()
            chain.proceed(request)
        } else {
            chain.proceed(chain.request())
        }
    }
}

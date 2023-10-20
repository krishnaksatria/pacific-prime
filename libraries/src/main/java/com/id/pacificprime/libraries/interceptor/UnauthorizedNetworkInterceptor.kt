package com.id.pacificprime.libraries.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class UnauthorizedNetworkInterceptor(
    private val unauthorizedListener: (Int, String) -> Unit
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val code = response.code()
        val message = response.message()
        if (code == UNAUTHORIZED) {
            //Trigger unauthorized listener
            unauthorizedListener.invoke(code, message)
        }
        return response
    }

    companion object {
        private const val UNAUTHORIZED = 401
    }
}

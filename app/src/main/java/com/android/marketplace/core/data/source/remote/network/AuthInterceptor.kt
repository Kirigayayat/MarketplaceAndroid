package com.android.marketplace.core.data.source.remote.network

import com.android.marketplace.util.Prefs
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .header("token", Prefs.token)
        return chain.proceed(requestBuilder.build())
    }
}
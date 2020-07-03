package com.coolshop.codechallenge.enoclink.data.network.mocking

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class MockInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("sessions/new") -> getLoginResponseJson
            uri.endsWith("avatar") -> getAvatarResponseJson
            uri.endsWith("users/test_id") -> getUserResponseJson
            else -> ""
        }

        return chain.proceed(chain.request())
                .newBuilder()
                .code(HTTP_OK)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(ResponseBody.create(MediaType.parse("application/json"),
                        responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
    }

}
package com.mayburger.gojekuiclone.data

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mayburger.gojekuiclone.constants.ApiRoutes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext


interface ApiHelper {

    companion object Factory {
        fun create(): ApiHelper {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            var sslContext: SSLContext? = null

            val httpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

//            if (BuildConfig.DEBUG) {
//                httpClient.addInterceptor(logging)
//                    .addInterceptor(ChuckInterceptor(Happy5Application.getInstance()).showNotification(true))
//            }
            val client = httpClient.build()

            val gson = GsonBuilder().disableHtmlEscaping().create()

            var baseUrl = ApiRoutes.BASE_URL

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(ApiHelper::class.java)
        }
    }
}
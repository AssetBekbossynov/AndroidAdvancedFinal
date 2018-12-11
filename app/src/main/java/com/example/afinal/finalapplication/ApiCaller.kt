package com.example.afinal.finalapplication

import com.example.afinal.finalapplication.service.Service
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APICaller {

    var apiEndpoint: Service

    init {

        val gson = GsonBuilder().create()

        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)

        val retrofit = Retrofit.Builder()
            //TODO: place url
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
            .build()


        apiEndpoint = retrofit.create(Service::class.java)
    }

    fun getTodoAPI(): Service{
        return apiEndpoint
    }

}
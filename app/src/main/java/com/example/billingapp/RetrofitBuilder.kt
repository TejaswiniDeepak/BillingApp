package com.example.billingapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object RetrofitBuilder {
    private const val BASE_URL="http://13.233.89.119:5000"

    private val client= OkHttpClient.Builder()
        //.connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT))
        .build()

       // .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
    //var client = OkHttpClient.Builder()

    private val gson: Gson = GsonBuilder().create()
    private val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun buildService():ItemsService = retrofit.create(ItemsService::class.java)
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}
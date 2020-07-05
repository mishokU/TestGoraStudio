package com.example.testgorastudio.data.remote.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object JsonApi {
    val retrofitService : JsonService by lazy {
        retrofit.create(JsonService::class.java)
    }
}
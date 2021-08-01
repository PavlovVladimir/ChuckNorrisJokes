package com.example.android.chucknorrisjokes.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.icndb.com/jokes/random/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

var loggingInterceptor = HttpLoggingInterceptor()

val retrofit = Retrofit.Builder()
    .client(
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor.apply {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    )
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface JokeApiService {
    @POST("{count}")
    fun getJokes(@Path("count") count: Int):
            Deferred<RandomJokesList>
}

object JokeApi {
    val retrofitService: JokeApiService by lazy {
        retrofit.create(JokeApiService::class.java)
    }
}
package com.example.android.chucknorrisjokes.network

import com.squareup.moshi.Json
import java.io.Serializable

data class Joke(
    @Json(name = "id") val id: Int,
    @Json(name = "joke") val joke: String,
) : Serializable
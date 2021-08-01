package com.example.android.chucknorrisjokes.network

import com.squareup.moshi.Json
import java.io.Serializable

data class RandomJokesList(
    @Json(name = "type") val type: String,
    @Json(name = "value") val jokeList: List<Joke>
) : Serializable
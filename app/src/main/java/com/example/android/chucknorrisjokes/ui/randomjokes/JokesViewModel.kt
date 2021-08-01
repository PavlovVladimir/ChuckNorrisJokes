package com.example.android.chucknorrisjokes.ui.randomjokes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.chucknorrisjokes.network.Joke
import com.example.android.chucknorrisjokes.network.JokeApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class JokesViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>>
        get() = _jokes

    private var _count: Int = 0
    var count = ""
        set(value) {
            checkAndSetCount(value)
        }

    private fun checkAndSetCount(value: String) {
        try {
            _count = value.toInt()
            if (_count < 0) {
                _count = 0
            }
        } catch (e: Exception) {
            _count = 0
        }
    }

    private fun loadJoke() {
        coroutineScope.launch {
            val getJokesDeferred = JokeApi.retrofitService.getJokes(_count)
            try {
                val result = getJokesDeferred.await()
                _jokes.value = result.jokeList
            } catch (e: Exception) {
                Log.d("JokesViewModel", "$e")
            }
        }
    }

    fun reload() {
        loadJoke()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
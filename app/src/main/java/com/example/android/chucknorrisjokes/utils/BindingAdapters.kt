package com.example.android.chucknorrisjokes.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chucknorrisjokes.network.Joke
import com.example.android.chucknorrisjokes.ui.randomjokes.JokesAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Joke>?) {
    val adapter = recyclerView.adapter as JokesAdapter
    adapter.submitList(data)
}

@BindingAdapter("setText")
fun bindText(textView: TextView, data: String?) {
    data?.let {
        textView.text = data
    }
}

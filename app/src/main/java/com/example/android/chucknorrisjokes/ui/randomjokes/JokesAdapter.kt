package com.example.android.chucknorrisjokes.ui.randomjokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chucknorrisjokes.databinding.ItemJokeBinding
import com.example.android.chucknorrisjokes.network.Joke

class JokesAdapter : ListAdapter<Joke, JokesAdapter.JokeViewHolder>(DiffCallBack) {

    class JokeViewHolder(private val binding: ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {
            binding.text = joke
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(ItemJokeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

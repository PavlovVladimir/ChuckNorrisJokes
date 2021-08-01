package com.example.android.chucknorrisjokes.ui.randomjokes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.example.android.chucknorrisjokes.databinding.FragmentJokesBinding

class JokesFragment : Fragment(), LifecycleOwner {

    private val viewModel: JokesViewModel by lazy {
        ViewModelProvider(this).get(JokesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentJokesBinding.inflate(inflater)
        binding.setLifecycleOwner { this.lifecycle }
        binding.viewModel = viewModel
        binding.jokesList.adapter = JokesAdapter()
        return binding.root
    }
}
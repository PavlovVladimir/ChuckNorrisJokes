package com.example.android.chucknorrisjokes.ui.randomjokes

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
        binding.button.setOnClickListener { loadAndHideKeyboard(it, viewModel) }
        return binding.root
    }

    private fun loadAndHideKeyboard(view: View, viewModel: JokesViewModel) {
        viewModel.reload()
        view.hideKeyboard()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
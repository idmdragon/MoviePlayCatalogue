package com.idmdragon.movie.ui

import com.idmdragon.base_ui.BaseFragment
import com.idmdragon.movie.databinding.FragmentMovieBinding
import com.idmdragon.movie.di.movieModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MovieFragment : BaseFragment<MovieViewModel,FragmentMovieBinding>() {

    override val viewModel: MovieViewModel by viewModel()

    override fun getViewBinding(): FragmentMovieBinding =
        FragmentMovieBinding.inflate(layoutInflater)

    override fun setUpView() {
        binding.apply {

        }
    }

    override fun loadInjectionModule() {
        loadKoinModules(movieModule)
    }
}
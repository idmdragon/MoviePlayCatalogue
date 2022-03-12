package com.idmdragon.movie.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.usecase.MovieUseCase
import com.idmdragon.domain.utils.Resource


class MovieViewModel(private val moviesUseCase: MovieUseCase) : ViewModel() {

    fun getMovieNowPlaying() : LiveData<Resource<List<Movie>>> =
        moviesUseCase.getMovieNowPlaying().asLiveData()

    fun getMoviePopular() : LiveData<Resource<List<Movie>>> =
        moviesUseCase.getMoviePopular().asLiveData()

    fun getMovieTopRated() : LiveData<Resource<List<Movie>>> =
        moviesUseCase.getMovieTopRated().asLiveData()

    fun getMovieUpComing() : LiveData<Resource<List<Movie>>> =
        moviesUseCase.getMovieUpcoming().asLiveData()
}
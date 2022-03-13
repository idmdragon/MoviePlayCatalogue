package com.idmdragon.movie.ui.viewModels

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.usecase.MovieUseCase
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movieplay.constant.ConstantExtras

class DetailViewModel(private val useCase: MovieUseCase) : ViewModel() {

    var movieId : Int? = null
    var movieType : String? = null

    fun getDetailMovie(movieId: Int, movieType: String): LiveData<Resource<MovieTv>> =
        useCase.getMovieDetail(movieId, movieType).asLiveData()

    fun processIntent(intent: Intent?) {
        intent?.apply {
            movieId = getIntExtra(ConstantExtras.EXTRAS_MOVIE_ID,0)
            movieType = getStringExtra(ConstantExtras.EXTRAS_MOVIE_TYPE)
        }
    }

}
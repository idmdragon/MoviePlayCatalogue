package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.repository.MovieRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class MovieUseCaseImpl(private val movieRepository: MovieRepository) : MovieUseCase {

    override fun getMovieNowPlaying(): Flow<Resource<List<MovieTv>>> =
        movieRepository.getMovieNowPlaying()

    override fun getMoviePopular(): Flow<Resource<List<MovieTv>>> =
        movieRepository.getMoviePopular()

    override fun getMovieTopRated(): Flow<Resource<List<MovieTv>>> =
        movieRepository.getMovieTopRated()

    override fun getMovieUpcoming(): Flow<Resource<List<MovieTv>>> =
        movieRepository.getMovieUpcoming()

    override fun getMovieDetail(movieId: Int, movieType: String): Flow<Resource<MovieTv>> =
        movieRepository.getMovieDetail(movieId = movieId, movieType = movieType)

    override fun clearData() : Unit =
        movieRepository.clearData()
}
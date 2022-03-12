package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.repository.MovieRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class MovieUseCaseImpl(private val movieRepository: MovieRepository) : MovieUseCase {

    override fun getMovieNowPlaying(): Flow<Resource<List<Movie>>> =
        movieRepository.getMovieNowPlaying()

    override fun getMoviePopular(): Flow<Resource<List<Movie>>> =
        movieRepository.getMoviePopular()

    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> =
        movieRepository.getMovieTopRated()

    override fun getMovieUpcoming(): Flow<Resource<List<Movie>>> =
        movieRepository.getMovieUpcoming()

    override fun getMovieDetail(movieId: Int, movieType: String): Flow<Resource<Movie>> =
        movieRepository.getMovieDetail(movieId = movieId, movieType = movieType)
}
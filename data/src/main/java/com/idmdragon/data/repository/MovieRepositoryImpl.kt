package com.idmdragon.data.repository

import com.idmdragon.data.source.local.MovieLocal
import com.idmdragon.data.source.remote.MovieRemote
import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.repository.MovieRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieRemote: MovieRemote,
    private val movieLocal: MovieLocal
): MovieRepository{
    override fun getMovieNowPlaying(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getMoviePopular(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getMovieUpcoming(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }
}
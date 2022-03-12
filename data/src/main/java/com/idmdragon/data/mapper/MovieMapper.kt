package com.idmdragon.data.mapper

import com.idmdragon.data.source.local.entities.MovieEntities
import com.idmdragon.data.source.remote.response.MovieResponse
import com.idmdragon.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun MovieResponse.toEntities(movieType: String): MovieEntities =
    MovieEntities(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        vote_count = vote_count,
        movie_type = movieType
    )

fun MovieEntities.toModel(): Movie =
    Movie(
        id = id,
        adult = adult,
        backdropPath = backdrop_path,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        releaseDate = release_date,
        title = title,
        voteAverage = vote_average,
        voteCount = vote_count,
        movieType = movie_type
    )

fun List<MovieEntities>.toModels(): List<Movie> =
    this.map {
        it.toModel()
    }

fun Flow<List<MovieEntities>>.toFlowModels(): Flow<List<Movie>> =
    this.map {
        it.toModels()
    }
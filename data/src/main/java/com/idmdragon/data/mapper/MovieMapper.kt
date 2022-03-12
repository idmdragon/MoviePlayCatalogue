package com.idmdragon.data.mapper

import com.idmdragon.data.source.local.entities.MovieTvEntities
import com.idmdragon.data.source.remote.response.MovieTvResponse
import com.idmdragon.domain.model.MovieTv
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun MovieTvResponse.toEntities(movieType: String): MovieTvEntities =
    MovieTvEntities(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title?:original_name.orEmpty(),
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date?:first_air_date.orEmpty(),
        title = title?:name.orEmpty(),
        vote_average = vote_average,
        vote_count = vote_count,
        movie_type = movieType
    )

fun MovieTvEntities.toModel(): MovieTv =
    MovieTv(
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

fun Flow<MovieTvEntities>.toFlowModel(): Flow<MovieTv> =
    this.map {
        it.toModel()
    }

fun List<MovieTvEntities>.toModels(): List<MovieTv> =
    this.map {
        it.toModel()
    }

fun Flow<List<MovieTvEntities>>.toFlowModels(): Flow<List<MovieTv>> =
    this.map {
        it.toModels()
    }
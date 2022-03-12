package com.idmdragon.data.mapper

import com.idmdragon.data.source.remote.response.SearchResponse
import com.idmdragon.domain.model.Search

fun SearchResponse.toModel(): Search =
    Search(
        id = id,
        posterPath = poster_path?:profile_path.orEmpty(),
        releaseDate = release_date.orEmpty(),
        mediaType = media_type,
        voteAverage = vote_average,
        title = original_title ?: original_name ?: name?: ""
    )

fun List<SearchResponse>.toModels(): List<Search> =
    this.map {
        it.toModel()
    }
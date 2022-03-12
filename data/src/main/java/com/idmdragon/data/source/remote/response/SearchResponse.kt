package com.idmdragon.data.source.remote.response

data class SearchResponse(
    val id: Int,
    val media_type: String,
    val original_name: String?,
    val original_title: String?,
    val overview: String,
    val release_date: String?,
    val vote_average: Double,
    val vote_count: Int,
    val poster_path: String?,
    val profile_path: String?,
    val name: String?
)
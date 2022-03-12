package com.idmdragon.domain.model

data class Search(
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val mediaType: String,
    val voteAverage:Double,
    val title:String
)
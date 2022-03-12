package com.idmdragon.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntities(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val movie_type: String
)
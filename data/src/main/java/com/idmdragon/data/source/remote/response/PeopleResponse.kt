package com.idmdragon.data.source.remote.response

data class PeopleResponse(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String?
)
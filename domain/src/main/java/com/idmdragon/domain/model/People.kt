package com.idmdragon.domain.model

data class People(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    val profilePath: String,
    val birthday: String?,
    val biography: String?,
    val place_of_birth: String?
)
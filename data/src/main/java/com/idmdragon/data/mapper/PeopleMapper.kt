package com.idmdragon.data.mapper

import com.idmdragon.data.source.remote.response.PeopleResponse
import com.idmdragon.data.source.remote.response.SearchResponse
import com.idmdragon.domain.model.People
import com.idmdragon.domain.model.Search

fun PeopleResponse.toModel(): People =
    People(
        id = id,
        profilePath = profile_path.orEmpty(),
        adult = adult,
        knownForDepartment = known_for_department,
        name = name,
        gender = gender,
        popularity = popularity
    )

fun List<PeopleResponse>.toModels(): List<People> =
    this.map {
        it.toModel()
    }
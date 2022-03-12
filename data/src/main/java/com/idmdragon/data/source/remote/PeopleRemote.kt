package com.idmdragon.data.source.remote

import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.PeopleResponse
import com.idmdragon.data.source.remote.response.SearchResponse
import com.idmdragon.data.source.remote.service.PeopleService
import com.idmdragon.data.source.remote.service.SearchService

class PeopleRemote(private val peopleService: PeopleService){

    suspend fun getPopularPeople(page: Int ): GeneralResponse<PeopleResponse> =
        peopleService.getPopularPeople(page = page, api_key = BuildConfig.API_KEY)
}
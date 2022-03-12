package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.PeopleResponse
import com.idmdragon.data.source.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {

    @GET("/3/person/popular")
    suspend fun getPopularPeople(
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
    ): GeneralResponse<PeopleResponse>


}
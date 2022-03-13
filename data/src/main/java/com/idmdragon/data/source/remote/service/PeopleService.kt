package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.PeopleResponse
import com.idmdragon.data.source.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

    @GET("/3/person/popular")
    suspend fun getPopularPeople(
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
    ): GeneralResponse<PeopleResponse>

    @GET("/3/person/{person_id}")
    suspend fun getDetailPeople(
        @Path("person_id") person_id: Int,
        @Query("api_key") api_key: String,
    ): PeopleResponse

}
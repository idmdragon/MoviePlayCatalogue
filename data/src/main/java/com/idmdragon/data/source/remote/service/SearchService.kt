package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/3/search/multi")
    suspend fun searchMovieTv(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): GeneralResponse<SearchResponse>


}
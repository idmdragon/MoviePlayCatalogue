package com.idmdragon.data.source.remote

import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.SearchResponse
import com.idmdragon.data.source.remote.service.SearchService

class SearchRemote(private val searchService: SearchService){

    suspend fun searchMovieTv(page: Int ,query: String): GeneralResponse<SearchResponse> =
        searchService.searchMovieTv(page = page, query = query, api_key = BuildConfig.API_KEY)
}
package com.idmdragon.data.source.remote

import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.response.*
import com.idmdragon.data.source.remote.service.PeopleService
import com.idmdragon.data.source.remote.service.SearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PeopleRemote(private val peopleService: PeopleService){

    suspend fun getPopularPeople(page: Int ): GeneralResponse<PeopleResponse> =
        peopleService.getPopularPeople(page = page, api_key = BuildConfig.API_KEY)

    fun getDetailPerson(personId:Int): Flow<ApiResponse<PeopleResponse>> =
        flow {
            try {
                val response = peopleService.getDetailPeople(personId, BuildConfig.API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}
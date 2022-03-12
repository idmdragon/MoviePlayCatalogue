package com.idmdragon.data.source.remote.response

data class GeneralResponse<DataType>(
    val page: Int,
    val results: List<DataType>,
    val total_pages: Int,
    val total_results: Int
)
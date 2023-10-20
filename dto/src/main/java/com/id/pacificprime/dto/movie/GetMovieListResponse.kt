package com.id.pacificprime.dto.movie

import com.google.gson.annotations.SerializedName

data class GetMovieListResponse(
    val dates: MovieDateData?,
    val page: Int?,
    val results: List<MovieDetailData>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)

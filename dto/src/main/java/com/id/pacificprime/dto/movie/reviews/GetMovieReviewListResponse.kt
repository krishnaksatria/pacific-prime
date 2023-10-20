package com.id.pacificprime.dto.movie.reviews

import com.google.gson.annotations.SerializedName

data class GetMovieReviewListResponse(
    val id: Int?,
    val page: Int?,
    val results: List<MovieReviewData>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)

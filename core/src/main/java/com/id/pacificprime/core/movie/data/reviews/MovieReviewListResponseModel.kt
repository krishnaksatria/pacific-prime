package com.id.pacificprime.core.movie.data.reviews

import com.id.pacificprime.dto.movie.reviews.GetMovieReviewListResponse

data class MovieReviewListResponseModel(
    val id: Int = 0,
    val page: Int = 0,
    val reviewList: List<MovieReviewModel> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

fun GetMovieReviewListResponse.toModel(): MovieReviewListResponseModel {
    return MovieReviewListResponseModel(
        id ?: 0,
        page ?: 0,
        results?.map { it.toModel() } ?: listOf(),
        totalPages ?: 0,
        totalResults ?: 0
    )
}

package com.id.pacificprime.core.movie.data

import com.id.pacificprime.dto.movie.GetMovieListResponse

data class MovieListResponseModel(
    val movieList: List<MovieDetailModel> = listOf(),
    val totalPages: Int = 0
)

fun GetMovieListResponse.toModel(): MovieListResponseModel {
    return MovieListResponseModel(
        results?.map { it.toModel() } ?: listOf(),
        totalPages ?: 0
    )
}

package com.id.pacificprime.core.movie.data.reviews

import com.id.pacificprime.dto.movie.reviews.MovieReviewData

data class MovieReviewModel(
    val authorDetails: AuthorModel = AuthorModel(),
    val content: String = "",
    val id: String = ""
)

fun MovieReviewData.toModel(): MovieReviewModel {
    return MovieReviewModel(
        authorDetails?.toModel() ?: AuthorModel(),
        content ?: "",
        id ?: ""
    )
}

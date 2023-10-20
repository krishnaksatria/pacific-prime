package com.id.pacificprime.core.movie.data.reviews

import com.id.pacificprime.dto.movie.reviews.AuthorData

data class AuthorModel(
    val name: String = "",
    val avatarPath: String = "",
    val rating: Float = 0F
)

fun AuthorData.toModel(): AuthorModel {
    val rating = rating ?: 0F
    val calculatedRating: Float = (rating / 2)
    return AuthorModel(
        name ?: "",
        avatarPath ?: "",
        calculatedRating
    )
}

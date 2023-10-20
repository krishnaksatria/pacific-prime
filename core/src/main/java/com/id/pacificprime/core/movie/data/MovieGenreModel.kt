package com.id.pacificprime.core.movie.data

import com.id.pacificprime.dto.movie.MovieGenreData

data class MovieGenreModel(
    val id: Int = 0,
    val name: String = ""
)

fun MovieGenreData.toModel(): MovieGenreModel {
    return MovieGenreModel(
        id ?: 0,
        name ?: ""
    )
}

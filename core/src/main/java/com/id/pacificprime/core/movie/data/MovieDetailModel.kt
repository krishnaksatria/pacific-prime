package com.id.pacificprime.core.movie.data

import com.id.pacificprime.dto.movie.MovieDetailData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MovieDetailModel(
    val adult: Boolean = false,
    val genreList: List<MovieGenreModel> = listOf(),
    val id: Int = 0,
    val overview: String = "",
    val popularity: Long = 0,
    val posterPath: String = "",
    val releaseDate: Date = DEFAULT_DATE,
    val title: String = "",
    val voteAverage: Long = 0,
    val voteCount: Int = 0
) {
    companion object {
        internal val DEFAULT_DATE = Date(0)
        internal const val DATE_FORMAT = "yyyy-mm-dd"
    }
}

fun MovieDetailData.toModel(): MovieDetailModel {
    val releaseDate = releaseDate ?: ""
    val parsedReleaseDate = if (releaseDate.isNotEmpty()) {
        val dateFormat = SimpleDateFormat(MovieDetailModel.DATE_FORMAT, Locale.getDefault())
        releaseDate.let { dateFormat.parse(it) } ?: MovieDetailModel.DEFAULT_DATE
    } else {
        MovieDetailModel.DEFAULT_DATE
    }

    return MovieDetailModel(
        adult ?: false,
        genres?.map { it.toModel() } ?: listOf(),
        id ?: 0,
        overview ?: "",
        (popularity ?: 0).toLong(),
        posterPath ?: "",
        parsedReleaseDate,
        title ?: "",
        (voteAverage ?: 0).toLong(),
        voteCount ?: 0
    )
}

package com.id.pacificprime.dto.movie

import com.google.gson.annotations.SerializedName

data class MovieDetailData(
    val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("belongs_to_collection") val belongsToCollection: BelongsToCollectionData?,
    val budget: Int?,
    val genres: List<MovieGenreData>?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    val homepage: String?,
    val id: Int?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanyData>?,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountryData>?,
    @SerializedName("release_date") val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguageData>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?
)

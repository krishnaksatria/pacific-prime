package com.id.pacificprime.dto.movie.reviews

import com.google.gson.annotations.SerializedName

data class MovieReviewData(
    val author: String?,
    @SerializedName("author_details") val authorDetails: AuthorData?,
    val content: String?,
    @SerializedName("created_at") val createdAt: String?,
    val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val url: String?
)

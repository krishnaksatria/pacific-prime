package com.id.pacificprime.dto.movie.reviews

import com.google.gson.annotations.SerializedName

data class AuthorData(
    val name: String?,
    val username: String?,
    @SerializedName("avatar_path") val avatarPath: String?,
    val rating: Float?
)

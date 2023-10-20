package com.id.pacificprime.dto.movie

import com.google.gson.annotations.SerializedName

data class SpokenLanguageData(
    @SerializedName("english_name") val englishName: String?,
    @SerializedName("iso_639_1") val iso: String?,
    val name: String?
)

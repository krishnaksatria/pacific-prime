package com.id.pacificprime.dto.movie

import com.google.gson.annotations.SerializedName

data class ProductionCountryData(
    @SerializedName("iso_3166_1") val iso: String?,
    val name: String?
)

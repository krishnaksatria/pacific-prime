package com.id.pacificprime.dto.movie

import com.google.gson.annotations.SerializedName

data class ProductionCompanyData(
    val id: Int?,
    @SerializedName("logo_path") val logoPath: String?,
    val name: String?,
    @SerializedName("origin_country") val originCountry: String?
)

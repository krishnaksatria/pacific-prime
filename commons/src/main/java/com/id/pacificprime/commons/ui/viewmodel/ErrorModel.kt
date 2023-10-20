package com.id.pacificprime.commons.ui.viewmodel

import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("status") var status: String? = "",
    @SerializedName("statusCode") var statusCode: Int? = 400,
    @SerializedName("errorCode") var errorCode: Int? = 400,
    @SerializedName("message") var message: String? = "",
)

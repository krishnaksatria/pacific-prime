package com.id.pacificprime.commons.ui.viewmodel

import com.id.pacificprime.libraries.interceptor.NoConnectivityException
import com.google.gson.Gson
import java.io.InterruptedIOException
import retrofit2.HttpException

fun getErrorMessage(throwable: Throwable): String? {
    return when (throwable) {
        is HttpException -> {
            val body = throwable.response()?.errorBody()?.string()
            try {
                val errorModel: ErrorModel? = Gson().fromJson(
                    body.toString(),
                    ErrorModel::class.java
                )
                errorModel?.message
            } catch (e: Exception) {
                e.message
            }
        }
        is InterruptedIOException -> {
            throwable.message
        }
        is NoConnectivityException -> {
            throwable.message
        }
        else -> ""
    }
}

fun BaseViewModel.updateViewStateModel(viewStateList: List<ViewStateModel>) {
    if (viewStateList.any { it == ViewStateModel.LOADING }) {
        if (viewState.value != ViewStateModel.LOADING) {
            viewState.value = ViewStateModel.LOADING
        }
    } else if (viewStateList.any { it == ViewStateModel.FAILED }) {
        viewState.value = ViewStateModel.FAILED
    } else if (viewStateList.all { it == ViewStateModel.SUCCESS }) {
        viewState.value = ViewStateModel.SUCCESS
    }
}

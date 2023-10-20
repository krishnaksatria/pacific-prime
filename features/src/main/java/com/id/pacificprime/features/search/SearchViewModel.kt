package com.id.pacificprime.features.search

import androidx.lifecycle.MutableLiveData
import com.id.pacificprime.commons.ui.viewmodel.BaseViewModel
import com.id.pacificprime.commons.ui.viewmodel.ViewStateModel
import com.id.pacificprime.commons.ui.viewmodel.getErrorMessage
import com.id.pacificprime.core.movie.data.MovieDetailModel
import com.id.pacificprime.core.movie.domain.MovieRepositoryContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel(
    private val movieRepositoryContract: MovieRepositoryContract
) : BaseViewModel() {
    val getMovieListLiveData: MutableLiveData<List<MovieDetailModel>> = MutableLiveData()

    var keyword: String = ""

    fun getMovieList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getMovieByKeyword(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    getMovieListLiveData.value = it
                    Timber.tag("testt").d("success")
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                    Timber.tag("testt").d("failed $it")
                }
            ).addToDisposable()
    }
}

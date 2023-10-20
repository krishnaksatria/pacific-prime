package com.id.pacificprime.features.dashboard

import androidx.lifecycle.MutableLiveData
import com.id.pacificprime.commons.ui.viewmodel.BaseViewModel
import com.id.pacificprime.commons.ui.viewmodel.ViewStateModel
import com.id.pacificprime.commons.ui.viewmodel.getErrorMessage
import com.id.pacificprime.core.movie.data.MovieDetailModel
import com.id.pacificprime.core.movie.domain.MovieRepositoryContract
import com.id.pacificprime.core.preferences.domain.PreferenceRepositoryContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DashboardViewModel(
    private val movieRepositoryContract: MovieRepositoryContract,
    private val preferenceRepositoryContract: PreferenceRepositoryContract
) : BaseViewModel() {
    val nowPlayingMovieListLiveData: MutableLiveData<List<MovieDetailModel>> = MutableLiveData()
    val popularMovieListLiveData: MutableLiveData<List<MovieDetailModel>> = MutableLiveData()
    val topRatedMovieListLiveData: MutableLiveData<List<MovieDetailModel>> = MutableLiveData()
    val upcomingMovieListLiveData: MutableLiveData<List<MovieDetailModel>> = MutableLiveData()

    fun getNowPlayingMovieList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getNowPlayingMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    nowPlayingMovieListLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }

    fun getPopularMovieList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getPopularMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    popularMovieListLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }

    fun getTopRatedMovieList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getTopRatedMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    topRatedMovieListLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }

    fun getUpcomingMovieList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getUpcomingMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    upcomingMovieListLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }

    fun saveToken(token: String) {
        preferenceRepositoryContract.setToken(token)
    }
}

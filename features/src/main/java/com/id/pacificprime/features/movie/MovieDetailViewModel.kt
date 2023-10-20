package com.id.pacificprime.features.movie

import androidx.lifecycle.MutableLiveData
import com.id.pacificprime.commons.ui.viewmodel.BaseViewModel
import com.id.pacificprime.commons.ui.viewmodel.ViewStateModel
import com.id.pacificprime.commons.ui.viewmodel.getErrorMessage
import com.id.pacificprime.core.movie.data.MovieDetailModel
import com.id.pacificprime.core.movie.data.reviews.MovieReviewModel
import com.id.pacificprime.core.movie.domain.MovieRepositoryContract
import com.id.pacificprime.room.data.FavoriteMovieData
import com.id.pacificprime.room.domain.RoomRepositoryContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(
    private val movieRepositoryContract: MovieRepositoryContract,
    private val roomRepositoryContract: RoomRepositoryContract
) : BaseViewModel() {
    val movieDetailLiveData: MutableLiveData<MovieDetailModel> = MutableLiveData()
    val movieReviewListLiveData: MutableLiveData<List<MovieReviewModel>> = MutableLiveData()
    val insertReminderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val removeReminderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val getFavoriteMovieListLiveData: MutableLiveData<List<FavoriteMovieData>> = MutableLiveData()

    var movieId: Int = 0
    var movie: MovieDetailModel = MovieDetailModel()
    var isFavorite: Boolean = false

    fun getMovieDetail() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    movieDetailLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }

    fun getMovieReviewList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getMovieReviewList(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    movieReviewListLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }

    fun insertFavoriteMovie(favoriteMovieData: FavoriteMovieData) {
        val id = roomRepositoryContract.insertFavoriteMovie(favoriteMovieData)
        insertReminderLiveData.value = id != MovieDetailActivity.INSERT_DATA_FAILED
    }

    fun removeFavoriteMovie(favoriteMovieData: FavoriteMovieData) {
        val id = roomRepositoryContract.removeFavoriteMovie(favoriteMovieData)
        removeReminderLiveData.value = id != MovieDetailActivity.CHANGE_DATA_SUCCESS
    }

    fun getFavoriteMovieList() {
        getFavoriteMovieListLiveData.value = roomRepositoryContract.getFavoriteMovieList()
    }
}

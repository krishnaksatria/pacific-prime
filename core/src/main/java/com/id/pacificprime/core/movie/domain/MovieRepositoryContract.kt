package com.id.pacificprime.core.movie.domain

import com.id.pacificprime.core.movie.data.MovieDetailModel
import com.id.pacificprime.core.movie.data.reviews.MovieReviewModel
import io.reactivex.rxjava3.core.Single

interface MovieRepositoryContract {
    fun getNowPlayingMovieList(): Single<List<MovieDetailModel>>
    fun getPopularMovieList(): Single<List<MovieDetailModel>>
    fun getTopRatedMovieList(): Single<List<MovieDetailModel>>
    fun getUpcomingMovieList(): Single<List<MovieDetailModel>>
    fun getMovieByKeyword(keyword: String): Single<List<MovieDetailModel>>
    fun getMovieDetail(id: Int): Single<MovieDetailModel>
    fun getMovieReviewList(id: Int): Single<List<MovieReviewModel>>
}

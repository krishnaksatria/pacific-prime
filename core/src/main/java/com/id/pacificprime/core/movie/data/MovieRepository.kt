package com.id.pacificprime.core.movie.data

import com.id.pacificprime.core.movie.data.reviews.MovieReviewModel
import com.id.pacificprime.core.movie.data.reviews.toModel
import com.id.pacificprime.core.movie.domain.MovieRepositoryContract
import io.reactivex.rxjava3.core.Single

class MovieRepository(
    private val movieService: MovieService
) : MovieRepositoryContract {
    override fun getNowPlayingMovieList(): Single<List<MovieDetailModel>> =
        movieService.getNowPlayingMovieList().map { it.toModel().movieList }

    override fun getPopularMovieList(): Single<List<MovieDetailModel>> =
        movieService.getPopularMovieList().map { it.toModel().movieList }

    override fun getTopRatedMovieList(): Single<List<MovieDetailModel>> =
        movieService.getTopRatedMovieList().map { it.toModel().movieList }

    override fun getUpcomingMovieList(): Single<List<MovieDetailModel>> =
        movieService.getUpcomingMovieList().map { it.toModel().movieList }

    override fun getMovieByKeyword(keyword: String): Single<List<MovieDetailModel>> =
        movieService.getMovieByKeyword(keyword).map { it.toModel().movieList }

    override fun getMovieDetail(id: Int): Single<MovieDetailModel> =
        movieService.getMovieDetail(id).map { it.toModel() }

    override fun getMovieReviewList(id: Int): Single<List<MovieReviewModel>> =
        movieService.getMovieReviewList(id).map { it.toModel().reviewList }
}

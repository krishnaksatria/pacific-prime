package com.id.pacificprime.core.movie.data

import com.id.pacificprime.dto.movie.GetMovieListResponse
import com.id.pacificprime.dto.movie.MovieDetailData
import com.id.pacificprime.dto.movie.reviews.GetMovieReviewListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    fun getNowPlayingMovieList(): Single<GetMovieListResponse>

    @GET("movie/popular")
    fun getPopularMovieList(): Single<GetMovieListResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovieList(): Single<GetMovieListResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovieList(): Single<GetMovieListResponse>

    @GET("search/movie")
    fun getMovieByKeyword(
        @Query("query") keyword: String
    ): Single<GetMovieListResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") movieId: Int
    ): Single<MovieDetailData>

    @GET("movie/{id}/reviews")
    fun getMovieReviewList(
        @Path("id") movieId: Int
    ): Single<GetMovieReviewListResponse>
}

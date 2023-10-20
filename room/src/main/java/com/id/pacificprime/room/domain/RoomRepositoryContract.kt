package com.id.pacificprime.room.domain

import com.id.pacificprime.room.data.FavoriteMovieData

interface RoomRepositoryContract {
    fun insertFavoriteMovie(movie: FavoriteMovieData): Long
    fun removeFavoriteMovie(movie: FavoriteMovieData): Int
    fun getFavoriteMovieList(): List<FavoriteMovieData>
}

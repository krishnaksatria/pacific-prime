package com.id.pacificprime.room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDao {
    @Insert
    fun insertFavoriteMovie(movie: FavoriteMovieData): Long

    @Delete
    fun removeFavoriteMovie(movie: FavoriteMovieData): Int

    @Query("select * from reminder_data")
    fun getFavoriteMovieList(): List<FavoriteMovieData>
}

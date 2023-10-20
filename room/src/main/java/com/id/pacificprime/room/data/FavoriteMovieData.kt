package com.id.pacificprime.room.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "reminder_data")
data class FavoriteMovieData(
    @PrimaryKey var id: Int = 0,
    var image: String = "",
    var title: String = "",
    var description: String = ""
) : Parcelable

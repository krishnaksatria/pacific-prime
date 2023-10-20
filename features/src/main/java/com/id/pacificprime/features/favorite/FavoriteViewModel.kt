package com.id.pacificprime.features.favorite

import androidx.lifecycle.MutableLiveData
import com.id.pacificprime.commons.ui.viewmodel.BaseViewModel
import com.id.pacificprime.room.data.FavoriteMovieData
import com.id.pacificprime.room.domain.RoomRepositoryContract

class FavoriteViewModel(
    private val roomRepositoryContract: RoomRepositoryContract
) : BaseViewModel() {
    val getFavoriteMovieListLiveData: MutableLiveData<List<FavoriteMovieData>> = MutableLiveData()

    fun getFavoriteMovieList() {
        getFavoriteMovieListLiveData.value = roomRepositoryContract.getFavoriteMovieList()
    }
}

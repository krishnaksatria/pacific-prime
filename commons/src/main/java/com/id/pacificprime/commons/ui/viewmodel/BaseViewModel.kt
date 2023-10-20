package com.id.pacificprime.commons.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.id.pacificprime.libraries.reactivex.DisposableViewModel

abstract class BaseViewModel : DisposableViewModel() {
    val viewState: MutableLiveData<ViewStateModel> = MutableLiveData()
    val showErrorLiveData: MutableLiveData<String> = MutableLiveData()
}

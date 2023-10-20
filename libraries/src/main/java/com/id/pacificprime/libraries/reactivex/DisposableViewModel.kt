package com.id.pacificprime.libraries.reactivex

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class DisposableViewModel : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun Disposable.addToDisposable() = compositeDisposable.add(this)

    fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}

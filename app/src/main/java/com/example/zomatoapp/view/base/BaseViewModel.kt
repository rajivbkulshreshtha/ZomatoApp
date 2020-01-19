package com.example.zomatoapp.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {


    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    val loadingDataLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
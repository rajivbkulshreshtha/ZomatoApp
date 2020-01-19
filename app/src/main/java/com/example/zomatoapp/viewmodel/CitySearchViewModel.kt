package com.example.zomatoapp.viewmodel

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.zomatoapp.data.repo.AppRepository
import com.example.zomatoapp.helpers.plus
import com.example.zomatoapp.data.model.cities.City
import com.example.zomatoapp.view.base.BaseViewModel
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class CitySearchViewModel(private val repository: AppRepository) :
    BaseViewModel() {


    val cityLiveData = MutableLiveData<List<City>>()
    val searchLiveData = MutableLiveData<String>()

    companion object {
        public const val TAG = "CitySearchViewModel"

    }


    fun searchCity(query: String) {

        loadingDataLiveData.postValue(true)
        compositeDisposable + repository.searchCity(query)
            .subscribe({
                Log.d(TAG, "searchCity-success:$it ");
                cityLiveData.postValue(it)
                loadingDataLiveData.postValue(false)
            }, {
                cityLiveData.postValue(null)
                loadingDataLiveData.postValue(false)
                it.printStackTrace()
                Log.d(TAG, "searchCity-error:${it.localizedMessage} ");
            })

    }


    fun searchListener(editText: EditText) {
        compositeDisposable + editText.textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                searchLiveData.postValue(it)
            }
    }

}
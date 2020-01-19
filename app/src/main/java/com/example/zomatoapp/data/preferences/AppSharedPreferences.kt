package me.ebraheem.restaurants.data.preferences

import androidx.lifecycle.MutableLiveData
import com.example.zomatoapp.data.model.SelectedCity


interface AppSharedPreferences {

    fun getSelectedCity(): SelectedCity

    val observableSelectedCity: MutableLiveData<SelectedCity>

    fun putSelectedCity(city: SelectedCity)

    companion object {
        val NONE_SELECTED_CITY = SelectedCity()
    }
}

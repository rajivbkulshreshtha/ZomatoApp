package com.example.zomatoapp.view.activity

import android.content.Intent
import android.location.Address
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zomatoapp.R
import com.example.zomatoapp.data.model.SelectedCity
import com.example.zomatoapp.data.model.cities.City
import com.example.zomatoapp.data.model.restaurants.Restaurant
import com.example.zomatoapp.helpers.*
import com.example.zomatoapp.view.adapter.RestaurantAdapter
import com.example.zomatoapp.view.base.BaseActivity
import com.example.zomatoapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import me.ebraheem.restaurants.data.preferences.AppPreferences
import me.ebraheem.restaurants.data.preferences.AppSharedPreferences.Companion.NONE_SELECTED_CITY
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : BaseActivity() {


    lateinit var viewModel: MainViewModel
    private var adapter: RestaurantAdapter? = null
    private val restaurants = mutableListOf<Restaurant>()
    private var permissionHelper = PermissionHelper(this, true)
    private val appPreferences: AppPreferences by inject()

    private val searchRequestCode = 1002


    companion object {
        public const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = getViewModel<MainViewModel>()
        viewModel.loadingDataLiveData.observe(this, LoadingLiveDataObserver())
        viewModel.restaurantLiveData.observe(this, RestaurantLiveDataObserver())
        viewModel.addressLiveData.observe(this, AddressLiveDataObserver())
        appPreferences.observableSelectedCity.observe(this, PreferenceLiveDataObserver())

        initRecyclerView()
        initView()


    }

    private fun initView() {
        filterResultButton.setOnClickListener {
            initPermission()
        }

        searchCard.setOnClickListener {
            if (isOnline(this)) {
                startActivityForResult(CitySearchActivity.getNewIntent(this), searchRequestCode)
            } else {
                showOfflineSnackbar(it)
            }
        }

        searchEditText.setOnClickListener {
            searchCard.performClick()
        }

        if (appPreferences.getSelectedCity() != NONE_SELECTED_CITY) {
            val city = appPreferences.getSelectedCity()

            viewModel.nearbyRestaurants(
                city.lat?.toDouble() ?: 0.0,
                city.lon?.toDouble() ?: 0.0,
                isOnline(this)
            )
        }

    }

    private fun initPermission() {
        /*if (isOnline(this)) {
            permissionHelper.onPermissionGranted = { doOnPermissionAccepted() }
        } else {
            showOfflineSnackbar(rootView(this))
        }
        permissionHelper.performPermissionCheck(this)*/

        permissionHelper.onPermissionGranted = { doOnPermissionAccepted() }
        permissionHelper.performPermissionCheck(this)


    }


    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }


    private fun setAdapter(restaurantList: List<Restaurant>?) {

        restaurants.clear()
        restaurantList?.let {
            restaurants.addAll(it)
            llNoData.hide()
        } ?: run {
            llNoData.show()
        }

        if (adapter == null) {
            adapter = RestaurantAdapter(this, restaurants)
            recyclerView.adapter = adapter
        }
        adapter?.notifyDataSetChanged()

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionHelper.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == searchRequestCode) {

            val city = data?.getParcelableExtra<City>(CitySearchActivity.EXTRA_CITY)
            city?.let {
                searchEditText.text = it.name!!
                viewModel.findLatLonOfCity(this, it.name!!)
            }
        }
    }

    private fun doOnPermissionAccepted() {

        getNearby()

    }


    private fun getNearby() {
        searchEditText.text = ""
        viewModel.nearbyRestaurantsByGPS(this)
    }

    inner class LoadingLiveDataObserver : Observer<Boolean> {

        override fun onChanged(loading: Boolean) {
            if (loading) {
                showProgress()
            } else {
                hideProgress()
            }
        }

    }

    inner class RestaurantLiveDataObserver : Observer<List<Restaurant>> {

        override fun onChanged(list: List<Restaurant>?) {
            setAdapter(list)
        }
    }

    inner class AddressLiveDataObserver : Observer<List<Address>> {

        override fun onChanged(t: List<Address>?) {
            val address = t?.first()
            address?.let {
                viewModel.nearbyRestaurants(it.latitude, it.longitude)
            }
        }
    }


    inner class PreferenceLiveDataObserver : Observer<SelectedCity> {

        override fun onChanged(selectedCity: SelectedCity?) {
            if (selectedCity == NONE_SELECTED_CITY) {

                searchAutomatically()

            } else {
                selectedCityName.text = selectedCity?.name
                selectedCityIcon.show()
            }

        }
    }

    fun searchAutomatically() {
        if (permissionHelper.checkPermissionGranted(this)) {
            if (isOnline(this)) {
                if (permissionHelper.statusCheck(this)) {
                    getNearby()
                } else {
                    permissionHelper.buildAlertMessageNoGps(this)
                }
            } else {
                showOfflineSnackbar(rootView(this))
            }
        } else {
            selectedCityName.text = "Select A City"
            selectedCityIcon.hide()
            llNoData.show()
        }
    }

    private fun showProgress() {
        shimmer_Scroll.show()
        recyclerView.hide()
    }

    private fun hideProgress() {
        shimmer_Scroll.hide()
        recyclerView.show()
    }

    private fun showOfflineSnackbar(view: View?) {

        view?.let {

            val snack = Snackbar.make(view, "Turn on the internet", Snackbar.LENGTH_LONG)
            snack.show()

        }

    }


}

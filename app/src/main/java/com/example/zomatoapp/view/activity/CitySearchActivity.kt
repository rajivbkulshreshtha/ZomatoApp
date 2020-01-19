package com.example.zomatoapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zomatoapp.R
import com.example.zomatoapp.data.model.cities.City
import com.example.zomatoapp.helpers.hide
import com.example.zomatoapp.helpers.show
import com.example.zomatoapp.view.adapter.CityAdapter
import com.example.zomatoapp.view.base.BaseActivity
import com.example.zomatoapp.viewmodel.CitySearchViewModel
import kotlinx.android.synthetic.main.activity_city_search.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class CitySearchActivity : BaseActivity(), CityAdapter.CityAdapterCallback {


    companion object {
        public const val TAG = "CitySearchActivity"
        const val EXTRA_CITY = "extra_city"

        fun getNewIntent(activity: Activity): Intent {
            return Intent(activity, CitySearchActivity::class.java)
        }

    }


    lateinit var viewModel: CitySearchViewModel
    private val cities = mutableListOf<City>()
    private var adapter: CityAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)

        initRecyclerView()

        viewModel = getViewModel<CitySearchViewModel>()
        viewModel.cityLiveData.observe(this, CityListObserver())
        viewModel.searchLiveData.observe(this, SearchObserver())
        viewModel.loadingDataLiveData.observe(this, LoadingLiveDataObserver())


        viewModel.searchListener(searchEditText)


    }


    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }


    private fun getCities(query: String) {
        viewModel.searchCity(query)
    }

    private fun setAdapter(cityList: List<City>?) {
        cities.clear()
        cityList?.let {
            cities.addAll(it)
        }

        if (adapter == null) {
            adapter = CityAdapter(this, cities)
            adapter?.cityAdapterCallback = this
            recyclerView.adapter = adapter
        }

        adapter?.notifyDataSetChanged()

    }

    inner class CityListObserver : Observer<List<City>> {

        override fun onChanged(cities: List<City>?) {
            if (cities!!.isEmpty()) {
                noResusltTextView.show()
            } else {
                noResusltTextView.hide()
            }
            setAdapter(cities)
        }

    }

    inner class SearchObserver : Observer<String> {

        override fun onChanged(it: String) {
            if (it.isNotEmpty()) {
                startSearchTextView.hide()
                getCities(it)
            } else {
                startSearchTextView.show()
                noResusltTextView.hide()
                setAdapter(null)
                swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    inner class LoadingLiveDataObserver : Observer<Boolean> {

        override fun onChanged(loading: Boolean) {
            swipeRefreshLayout.isRefreshing = loading
        }

    }

    override fun citySelected(position: Int, city: City) {

        val resultIntent = Intent().putExtra(EXTRA_CITY, city)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }


}

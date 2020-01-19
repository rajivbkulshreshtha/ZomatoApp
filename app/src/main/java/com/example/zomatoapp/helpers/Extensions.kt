package com.example.zomatoapp.helpers

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.io.IOException

operator fun CompositeDisposable?.plus(disposable: Disposable?): CompositeDisposable? {
    disposable?.let {
        this?.add(it)
    }
    return this
}


val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)


inline fun View.hide() {
    this.visibility = View.GONE
}

inline fun View.show() {
    this.visibility = View.VISIBLE
}

fun isOnline(context: Context?): Boolean {
    return try {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo;
        netInfo != null && netInfo.isConnected
    } catch (e: IOException) {
        Log.d("isOnline", "isOnline-error:$e");
        false
    }
    return false

}




inline fun rootView(activity: Activity): View? {
    return activity.findViewById<View>(android.R.id.content)
}



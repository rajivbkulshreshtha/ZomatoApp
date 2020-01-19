package com.example

import androidx.multidex.MultiDexApplication
import com.example.koin.di.zomatoApp
import com.example.zomatoapp.R
import com.facebook.stetho.Stetho
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin


class MyApplication : MultiDexApplication() {

    companion object {
        public const val TAG = "MyApplication"

    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this);

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/gotham_rounded_medium.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )

        startKoin {
            printLogger()

            androidContext(this@MyApplication)
            androidFileProperties()
            modules(zomatoApp)

        }
    }

}
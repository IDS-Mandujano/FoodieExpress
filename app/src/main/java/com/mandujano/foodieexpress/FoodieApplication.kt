package com.mandujano.foodieexpress

import android.app.Application
import com.mandujano.foodieexpress.core.di.AppContainer
import com.mandujano.foodieexpress.core.di.DefaultAppContainer

class FoodieApplication : Application() {
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }


}
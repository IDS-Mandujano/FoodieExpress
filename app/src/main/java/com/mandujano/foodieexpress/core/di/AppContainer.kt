package com.mandujano.foodieexpress.core.di

import com.mandujano.foodieexpress.features.menu.data.remote.FoodieApi
import com.mandujano.foodieexpress.features.menu.data.repository.MenuRepositoryImpl
import com.mandujano.foodieexpress.features.menu.domain.repository.MenuRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer {
    val menuRepository : MenuRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: FoodieApi by lazy {
        retrofit.create(FoodieApi::class.java)
    }

    override val menuRepository : MenuRepository by lazy {
        MenuRepositoryImpl(retrofitService)
    }

}
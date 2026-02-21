package com.mandujano.foodieexpress.features.menu.data.datasources.remote.api

import com.mandujano.foodieexpress.features.menu.data.datasources.remote.models.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodieExpressApi {

    @GET("search.php?s=")
    suspend fun getMenu(): MealsResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String) : MealsResponse

}
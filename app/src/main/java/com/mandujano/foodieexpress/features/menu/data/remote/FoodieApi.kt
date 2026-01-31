package com.mandujano.foodieexpress.features.menu.data.remote

import com.mandujano.foodieexpress.features.menu.data.dto.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodieApi {
    @GET("search.php?s=")
    suspend fun getMenu(): MealsResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String) : MealsResponse
}
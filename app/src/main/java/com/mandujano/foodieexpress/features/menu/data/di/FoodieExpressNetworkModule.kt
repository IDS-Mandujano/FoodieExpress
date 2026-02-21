package com.mandujano.foodieexpress.features.menu.data.di

import com.mandujano.foodieexpress.core.di.FoodieExpressRetrofit
import com.mandujano.foodieexpress.features.menu.data.datasources.remote.api.FoodieExpressApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodieExpressNetworkModule {
    @Provides
    @Singleton
    fun provideFoodieExpressApi(@FoodieExpressRetrofit retrofit: Retrofit): FoodieExpressApi {
        return retrofit.create(FoodieExpressApi::class.java)
    }
}
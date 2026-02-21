package com.mandujano.foodieexpress.features.menu.data.di

import com.mandujano.foodieexpress.features.menu.data.repositories.DishRepositoryImpl
import com.mandujano.foodieexpress.features.menu.domain.repositories.DishRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(
        dishRepositoryImpl: DishRepositoryImpl
    ): DishRepository
}
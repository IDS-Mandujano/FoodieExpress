package com.mandujano.foodieexpress.features.menu.domain.repository

import com.mandujano.foodieexpress.features.menu.domain.model.Dish
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getDishes() : Flow<List<Dish>>
    suspend fun getDishById(id : String) : Flow<Dish>
}
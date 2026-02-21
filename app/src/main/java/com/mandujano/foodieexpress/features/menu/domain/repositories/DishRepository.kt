package com.mandujano.foodieexpress.features.menu.domain.repositories

import com.mandujano.foodieexpress.features.menu.domain.entities.Dish
import kotlinx.coroutines.flow.Flow

interface DishRepository {
    suspend fun getDishes(): Flow<List<Dish>>
    suspend fun getDishById(id: String): Flow<Dish>

}
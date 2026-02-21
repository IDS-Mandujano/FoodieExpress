package com.mandujano.foodieexpress.features.menu.data.repositories

import com.mandujano.foodieexpress.features.menu.data.datasources.remote.api.FoodieExpressApi
import com.mandujano.foodieexpress.features.menu.data.datasources.remote.mapper.toDomain
import com.mandujano.foodieexpress.features.menu.domain.entities.Dish
import com.mandujano.foodieexpress.features.menu.domain.repositories.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DishRepositoryImpl @Inject constructor(
    private val api: FoodieExpressApi
) : DishRepository {

    override suspend fun getDishes(): Flow<List<Dish>> = flow {

        val response = api.getMenu()
        val remoteDishes = response.meals ?: emptyList()
        val domainDishes = remoteDishes.map { it.toDomain() }

        emit(domainDishes)
    }

    override suspend fun getDishById(id: String): Flow<Dish> = flow {

        val response = api.getMealById(id)
        val dto = response.meals?.firstOrNull()
        if (dto != null) {
            emit(dto.toDomain())
        } else {
            throw Exception("No se encontró el plato con el ID especificado")
        }
    }

}
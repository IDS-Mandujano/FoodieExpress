package com.mandujano.foodieexpress.features.menu.data.repository

import com.mandujano.foodieexpress.features.menu.data.mapper.toDomain
import com.mandujano.foodieexpress.features.menu.data.remote.FoodieApi
import com.mandujano.foodieexpress.features.menu.domain.model.Dish
import com.mandujano.foodieexpress.features.menu.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MenuRepositoryImpl(
    private val api: FoodieApi
) : MenuRepository {

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
package com.mandujano.foodieexpress.features.menu.domain.usecase

import com.mandujano.foodieexpress.features.menu.domain.entities.Dish
import com.mandujano.foodieexpress.features.menu.domain.repositories.DishRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetDishUseCase @Inject constructor(
    private val repository: DishRepository
) {

    suspend operator fun invoke(): Result<List<Dish>> {
        return try {
            val dishes = repository.getDishes().first()
            val filteredDishes = dishes.filter { it.name.isNotBlank() }

            if (filteredDishes.isEmpty()){
                Result.failure(Exception("No se encontraron platos"))
            } else {
                Result.success(filteredDishes)
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}

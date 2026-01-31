package com.mandujano.foodieexpress.features.menu.domain.usecase

import com.mandujano.foodieexpress.features.menu.domain.model.Dish
import com.mandujano.foodieexpress.features.menu.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow

class GetDishByIdUseCase(private val repository: MenuRepository){
    suspend operator fun invoke(id: String) : Flow<Dish> {
        return repository.getDishById(id)
    }
}
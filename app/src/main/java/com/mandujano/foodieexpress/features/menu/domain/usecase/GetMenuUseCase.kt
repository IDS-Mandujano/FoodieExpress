package com.mandujano.foodieexpress.features.menu.domain.usecase

import com.mandujano.foodieexpress.features.menu.domain.model.Dish
import com.mandujano.foodieexpress.features.menu.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow

class GetMenuUseCase (private val repository: MenuRepository) {
    suspend operator fun invoke(): Flow<List<Dish>> {
        return repository.getDishes()
    }
}

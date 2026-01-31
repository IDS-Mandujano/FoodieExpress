package com.mandujano.foodieexpress.features.menu.data.mapper

import com.mandujano.foodieexpress.features.menu.data.dto.DishDto
import com.mandujano.foodieexpress.features.menu.domain.model.Dish

fun DishDto.toDomain(): Dish {
    return Dish(

        id = this.id.toIntOrNull() ?: this.id.hashCode(),
        name = this.name,
        description = if (this.instructions.length > 100) "${this.instructions.take(100)}..." else this.instructions,
        price = (50..200).random().toDouble(),
        imageUrl = this.imageUrl
    )
}
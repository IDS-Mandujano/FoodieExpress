package com.mandujano.foodieexpress.features.menu.presentation.viewmodels

import com.mandujano.foodieexpress.features.menu.domain.model.Dish

sealed interface MenuUiState {

    data object Loading : MenuUiState

    data class Succes (val dishes : List<Dish>) : MenuUiState

    data object Error : MenuUiState
}
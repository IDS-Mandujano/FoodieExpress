package com.mandujano.foodieexpress.features.menu.presentation.viewmodels // <--- OJO AQUÍ: viewmodels (PLURAL)

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mandujano.foodieexpress.FoodieApplication
import com.mandujano.foodieexpress.features.menu.domain.model.Dish
import com.mandujano.foodieexpress.features.menu.domain.usecase.GetDishByIdUseCase
import kotlinx.coroutines.launch

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Success(val dish: Dish) : DetailUiState
    data object Error : DetailUiState
}

class DetailViewModel(
    private val getDishByIdUseCase: GetDishByIdUseCase
) : ViewModel() {

    var uiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    fun loadDish(id: String) {
        viewModelScope.launch {
            uiState = DetailUiState.Loading
            try {
                getDishByIdUseCase(id).collect { dish ->
                    uiState = DetailUiState.Success(dish)
                }
            } catch (e: Exception) {
                uiState = DetailUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as FoodieApplication)
                val repo = app.container.menuRepository
                DetailViewModel(GetDishByIdUseCase(repo))
            }
        }
    }
}
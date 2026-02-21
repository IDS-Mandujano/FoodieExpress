package com.mandujano.foodieexpress.features.menu.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandujano.foodieexpress.features.menu.domain.entities.Dish
import com.mandujano.foodieexpress.features.menu.domain.usecase.GetDishByIdUseCase
import com.mandujano.foodieexpress.features.menu.domain.usecase.GetDishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MenuUiState(
    val isLoading: Boolean = false,
    val dishes: List<Dish> = emptyList(),
    val selectedDish: Dish? = null,
    val error: String? = null
)

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getDishUseCase: GetDishUseCase,
    private val getDishByIdUseCase: GetDishByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState: StateFlow<MenuUiState> = _uiState

    fun getDishes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val result = getDishUseCase()
            result.onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, dishes = it)
            }.onFailure {
                _uiState.value = _uiState.value.copy(isLoading = false, error = it.message)
            }
        }
    }

    fun getDishById(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val result = getDishByIdUseCase(id)
            result.onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, selectedDish = it.firstOrNull())
            }.onFailure {
                _uiState.value = _uiState.value.copy(isLoading = false, error = it.message)
            }
        }
    }
}
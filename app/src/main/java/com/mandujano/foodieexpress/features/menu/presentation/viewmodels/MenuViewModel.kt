package com.mandujano.foodieexpress.features.menu.presentation.viewmodels

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
import com.mandujano.foodieexpress.features.menu.domain.usecase.GetMenuUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MenuViewModel (
    private val getMenuUseCase: GetMenuUseCase
) : ViewModel() {

    var uiState : MenuUiState by mutableStateOf(MenuUiState.Loading)
        private set

    init {
        getMenu()
    }

    fun getMenu() {
        viewModelScope.launch {

            uiState = MenuUiState.Loading

            getMenuUseCase()
                .catch {
                    uiState = MenuUiState.Error
                }
                .collect { dishes ->
                    uiState = MenuUiState.Succes(dishes)
                }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodieApplication)

                val repository = application.container.menuRepository

                MenuViewModel (
                    getMenuUseCase = GetMenuUseCase(repository)
                )
            }
        }
    }

}
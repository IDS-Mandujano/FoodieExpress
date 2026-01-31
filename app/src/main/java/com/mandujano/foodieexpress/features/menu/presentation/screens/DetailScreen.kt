package com.mandujano.foodieexpress.features.menu.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

import com.mandujano.foodieexpress.features.menu.presentation.viewmodels.DetailUiState
import com.mandujano.foodieexpress.features.menu.presentation.viewmodels.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    dishId: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
) {

    LaunchedEffect(dishId) {
        viewModel.loadDish(dishId)
    }

    val state = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (state) {
                is DetailUiState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                is DetailUiState.Error -> Text("Error al cargar", Modifier.align(Alignment.Center))
                is DetailUiState.Success -> DishDetailContent(state.dish)
            }
        }
    }
}

@Composable
fun DishDetailContent(dish: com.mandujano.foodieexpress.features.menu.domain.model.Dish) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = dish.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(250.dp),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(dish.name, style = MaterialTheme.typography.headlineMedium)
            Text("$${dish.price}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Instrucciones:", style = MaterialTheme.typography.titleMedium)
            Text(dish.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
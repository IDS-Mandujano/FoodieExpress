package com.mandujano.foodieexpress.features.menu.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.mandujano.foodieexpress.features.menu.domain.model.Dish
import com.mandujano.foodieexpress.features.menu.presentation.viewmodels.MenuUiState
import com.mandujano.foodieexpress.features.menu.presentation.viewmodels.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    viewModel: MenuViewModel = viewModel(factory = MenuViewModel.Factory),
    onDishClick: (String) -> Unit
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Foodie Express 🍔") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is MenuUiState.Loading -> CircularProgressIndicator()
                is MenuUiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error al cargar menu!")
                        Button(onClick = { viewModel.getMenu() }) { Text("Reintentar") }
                    }
                }
                is MenuUiState.Succes -> {
                    DishList(dishes = uiState.dishes, onDishClick = onDishClick)
                }
            }
        }
    }
}

@Composable
fun DishList(dishes: List<Dish>, onDishClick: (String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dishes) { dish ->
            DishCard(dish = dish, onClick = { onDishClick(dish.id.toString()) })
        }
    }
}

@Composable
fun DishCard(dish: Dish, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = dish.imageUrl,
                contentDescription = dish.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = dish.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "$${dish.price}", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2
                )
            }
        }
    }
}
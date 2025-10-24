package com.raphael.magicfolder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raphael.magicfolder.ui.*
import com.raphael.magicfolder.ui.screens.CardsScreen
import com.raphael.magicfolder.ui.screens.CategoriesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { AppRoot() }
    }
}

@Composable
fun AppRoot() {
    MaterialTheme {
        val navController = rememberNavController()
        val current = navController.currentBackStackEntryAsState().value?.destination?.route

        val cardsVM: CardsViewModel = viewModel(factory = CardsVMFactory())
        val categoryVM: CategoryViewModel = viewModel(factory = CategoryVMFactory())

        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = current == "cards",
                        onClick = { navController.navigate("cards") },
                        label = { Text("Cards") },
                        icon = { Icon(AppIcons.List, contentDescription = null) }
                    )
                    NavigationBarItem(
                        selected = current == "categories",
                        onClick = { navController.navigate("categories") },
                        label = { Text("Categorias") },
                        icon = { Icon(AppIcons.Category, contentDescription = null) }
                    )
                }
            }
        ) { padding ->
            NavHost(navController, startDestination = "cards", modifier = Modifier.padding(padding)) {
                composable("cards") { Surface(tonalElevation = 1.dp) { CardsScreen(cardsVM) } }
                composable("categories") { Surface(tonalElevation = 1.dp) { CategoriesScreen(categoryVM) } }
            }
        }
    }
}

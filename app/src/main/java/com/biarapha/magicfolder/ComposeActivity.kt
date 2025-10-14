package com.biarapha.magicfolder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ViewList
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.biarapha.magicfolder.ui.CollectionScreen
import com.biarapha.magicfolder.ui.EditCardScreen
import com.biarapha.magicfolder.ui.NewCardScreen
import com.biarapha.magicfolder.ui.WishlistScreen
import com.biarapha.magicfolder.vm.CardViewModel

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MagicFolderApp() }
    }
}

private sealed class Tab(val route: String, val label: String, val icon: ImageVector) {
    data object Collection : Tab("collection", "Coleção", Icons.AutoMirrored.Outlined.ViewList)
    data object Wishlist   : Tab("wishlist",   "Wishlist", Icons.Outlined.FavoriteBorder)
    data object New        : Tab("new",        "Nova",     Icons.Outlined.AddBox)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicFolderApp() {
    val nav = rememberNavController()

    // ViewModel ÚNICO compartilhado entre as telas
    val app = LocalContext.current.applicationContext as android.app.Application
    val vm: CardViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(app)
    )

    val tabs = listOf(Tab.Collection, Tab.Wishlist, Tab.New)
    val back by nav.currentBackStackEntryAsState()
    val currentRoute = back?.destination?.route ?: Tab.Collection.route

    val title = when {
        currentRoute.startsWith(Tab.Collection.route) -> "Coleção"
        currentRoute.startsWith(Tab.Wishlist.route)   -> "Wishlist"
        currentRoute.startsWith(Tab.New.route)        -> "Nova carta"
        currentRoute.startsWith("edit/")              -> "Editar carta"
        else                                          -> "MagicFolder"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        bottomBar = {
            NavigationBar {
                tabs.forEach { t ->
                    NavigationBarItem(
                        selected = currentRoute == t.route,
                        onClick = {
                            if (currentRoute != t.route) {
                                nav.navigate(t.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(nav.graph.startDestinationId) { saveState = true }
                                }
                            }
                        },
                        icon = { Icon(t.icon, contentDescription = t.label) },
                        label = { Text(t.label) }
                    )
                }
            }
        }
    ) { inner ->
        Surface(modifier = Modifier.padding(inner)) {
            NavHost(
                navController = nav,
                startDestination = Tab.Collection.route
            ) {
                composable(Tab.Collection.route) {
                    CollectionScreen(
                        onAdd = { nav.navigate(Tab.New.route) },
                        onEdit = { id -> nav.navigate("edit/$id") },
                        vm = vm
                    )
                }
                composable(Tab.Wishlist.route) {
                    WishlistScreen(onBack = { nav.navigateUp() }, vm = vm)
                }
                composable(Tab.New.route) {
                    NewCardScreen(
                        onCancel = { nav.navigateUp() },
                        onSaved = {
                            nav.popBackStack()
                            nav.navigate(Tab.Collection.route)
                        },
                        vm = vm
                    )
                }
                composable("edit/{id}") { backStack ->
                    val id = backStack.arguments?.getString("id")?.toLongOrNull() ?: 0L
                    EditCardScreen(
                        id = id,
                        onSaved = { nav.popBackStack() },
                        vm = vm
                    )
                }
            }
        }
    }
}

package com.biarapha.magicfolder.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.biarapha.magicfolder.vm.CardViewModel

@Composable
fun WishlistScreen(
    onBack: () -> Unit,
    vm: CardViewModel
) {
    val items by vm.wishlist.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Wishlist", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            OutlinedButton(onClick = onBack, shape = MaterialTheme.shapes.extraLarge) {
                Text("Voltar")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(items) { c ->
                ElevatedCard(shape = MaterialTheme.shapes.extraLarge) {
                    Column(Modifier.padding(16.dp)) {
                        Text(c.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Text("Custo: ${c.manaCost}")
                        Text("Tipo: ${c.type}")
                        Text("Cores: ${c.colors.ifBlank { "—" }}")
                        Spacer(Modifier.height(10.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = { vm.toggleWishlist(c.id) }) { Text("Wishlist") }
                            Spacer(Modifier.width(8.dp))
                            TextButton(onClick = { vm.delete(c.id) }) { Text("Excluir") }
                        }
                    }
                }
            }
        }
    }
}

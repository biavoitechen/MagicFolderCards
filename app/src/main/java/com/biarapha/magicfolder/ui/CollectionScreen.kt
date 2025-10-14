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
fun CollectionScreen(
    onAdd: () -> Unit,
    onEdit: (Long) -> Unit,
    vm: CardViewModel
) {
    val cards by vm.cards.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Coleção", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                Text("Total: ${cards.size} cartas", style = MaterialTheme.typography.bodyMedium)
            }
            Button(onClick = onAdd, shape = MaterialTheme.shapes.extraLarge) {
                Text("Adicionar")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(cards) { c ->
                ElevatedCard(shape = MaterialTheme.shapes.extraLarge) {
                    Column(Modifier.padding(16.dp)) {
                        Text(c.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Text("Custo: ${c.manaCost}")
                        Text("Tipo: ${c.type}")
                        Text("Cores: ${c.colors.ifBlank { "—" }}")
                        if (c.inWishlist) {
                            Spacer(Modifier.height(6.dp))
                            Text("⭐ Na wishlist", style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(Modifier.height(10.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { vm.toggleWishlist(c.id) }) { Text("Wishlist") }
                            Spacer(Modifier.width(8.dp))
                            TextButton(onClick = { onEdit(c.id) }) { Text("Editar") }
                            Spacer(Modifier.width(8.dp))
                            TextButton(onClick = { vm.delete(c.id) }) { Text("Excluir") }
                        }
                    }
                }
            }
        }
    }
}

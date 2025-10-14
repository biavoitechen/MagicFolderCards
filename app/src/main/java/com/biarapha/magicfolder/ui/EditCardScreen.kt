package com.biarapha.magicfolder.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biarapha.magicfolder.vm.CardViewModel

@Composable
fun EditCardScreen(
    id: Long,
    onSaved: () -> Unit,
    vm: CardViewModel
) {
    val card by vm.observeById(id).collectAsState(initial = null)

    var name by remember(card) { mutableStateOf(card?.name.orEmpty()) }
    var mana by remember(card) { mutableStateOf(card?.manaCost.orEmpty()) }
    var type by remember(card) { mutableStateOf(card?.type.orEmpty()) }
    var colors by remember(card) { mutableStateOf(card?.colors.orEmpty()) }
    var inWishlist by remember(card) { mutableStateOf(card?.inWishlist ?: false) }

    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Editar carta", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(name, { name = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(mana, { mana = it }, label = { Text("Custo de Mana") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(type, { type = it }, label = { Text("Tipo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(colors, { colors = it }, label = { Text("Cores") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))
        OutlinedButton(onClick = { inWishlist = !inWishlist }) {
            Text(if (inWishlist) "⭐ Wishlist" else "Wishlist")
        }

        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    vm.save(
                        id = id,
                        name = name.trim(),
                        manaCost = mana.trim(),
                        type = type.trim(),
                        colors = colors.trim(),
                        inWishlist = inWishlist
                    )
                    onSaved()
                },
                enabled = name.isNotBlank()
            ) { Text("Salvar") }

            OutlinedButton(onClick = onSaved) { Text("Cancelar") }
        }
    }
}

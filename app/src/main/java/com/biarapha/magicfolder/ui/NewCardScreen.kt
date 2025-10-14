package com.biarapha.magicfolder.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biarapha.magicfolder.vm.CardViewModel

@Composable
fun NewCardScreen(
    onCancel: () -> Unit,
    onSaved: () -> Unit,
    vm: CardViewModel
) {
    var name by remember { mutableStateOf("") }
    var mana by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var colors by remember { mutableStateOf("") }
    var inWishlist by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Nova carta", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(name, { name = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(mana, { mana = it }, label = { Text("Custo de Mana") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(type, { type = it }, label = { Text("Tipo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(colors, { colors = it }, label = { Text("Cores (separadas por vírgula)") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))
        OutlinedButton(onClick = { inWishlist = !inWishlist }) {
            Text(if (inWishlist) "⭐ Wishlist" else "Wishlist")
        }

        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    vm.save(
                        id = 0L,
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

            OutlinedButton(onClick = onCancel) { Text("Cancelar") }
        }
    }
}

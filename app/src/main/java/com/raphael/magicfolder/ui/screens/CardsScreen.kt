package com.raphael.magicfolder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.raphael.magicfolder.data.local.entity.Card
import com.raphael.magicfolder.ui.CardsViewModel

@Composable
fun CardsScreen(vm: CardsViewModel) {
    val cards by vm.cards.collectAsState()
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var notes by remember { mutableStateOf(TextFieldValue("")) }
    var editing: Card? by remember { mutableStateOf(null) }

    Column(Modifier.padding(16.dp)) {
        Text(if (editing == null) "Novo Card" else "Editar Card", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Título") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notas") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Row {
            Button(onClick = {
                if (editing == null) vm.add(title.text.trim(), notes.text.trim(), null)
                else vm.update(editing!!.copy(title = title.text.trim(), notes = notes.text.trim()))
                title = TextFieldValue(""); notes = TextFieldValue(""); editing = null
            }) { Text(if (editing == null) "Adicionar" else "Salvar") }
            Spacer(Modifier.width(8.dp))
            if (editing != null) {
                OutlinedButton(onClick = { editing = null; title = TextFieldValue(""); notes = TextFieldValue("") }) { Text("Cancelar") }
            }
        }
        Spacer(Modifier.height(16.dp)); Divider(); Spacer(Modifier.height(8.dp))
        Text("Cards", style = MaterialTheme.typography.titleLarge); Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(cards) { c -> CardItem(c, onEdit = { editing = it; title = TextFieldValue(it.title); notes = TextFieldValue(it.notes) }, onDelete = { vm.delete(it) }) }
        }
    }
}

@Composable
private fun CardItem(item: Card, onEdit: (Card) -> Unit, onDelete: (Card) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        Column(Modifier.padding(12.dp)) {
            Text(item.title, style = MaterialTheme.typography.titleMedium)
            if (item.notes.isNotBlank()) { Spacer(Modifier.height(4.dp)); Text(item.notes, style = MaterialTheme.typography.bodyMedium) }
            Spacer(Modifier.height(8.dp))
            Row { OutlinedButton(onClick = { onEdit(item) }) { Text("Editar") }; Spacer(Modifier.width(8.dp)); OutlinedButton(onClick = { onDelete(item) }) { Text("Excluir") } }
        }
    }
}

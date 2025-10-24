package com.raphael.magicfolder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.raphael.magicfolder.data.local.entity.Category
import com.raphael.magicfolder.ui.CategoryViewModel

@Composable
fun CategoriesScreen(vm: CategoryViewModel) {
    val categories by vm.categories.collectAsState()
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var editing: Category? by remember { mutableStateOf(null) }

    Column(Modifier.padding(16.dp)) {
        Text(if (editing == null) "Nova Categoria" else "Editar Categoria", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descrição") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Row {
            Button(onClick = {
                if (editing == null) vm.add(name.text.trim(), description.text.trim())
                else vm.update(editing!!.copy(name = name.text.trim(), description = description.text.trim()))
                name = TextFieldValue(""); description = TextFieldValue(""); editing = null
            }) { Text(if (editing == null) "Adicionar" else "Salvar") }
            Spacer(Modifier.width(8.dp))
            if (editing != null) {
                OutlinedButton(onClick = { editing = null; name = TextFieldValue(""); description = TextFieldValue("") }) { Text("Cancelar") }
            }
        }
        Spacer(Modifier.height(16.dp)); Divider(); Spacer(Modifier.height(8.dp))
        Text("Categorias", style = MaterialTheme.typography.titleLarge); Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(categories) { cat -> CategoryItem(cat, onEdit = { editing = it; name = TextFieldValue(it.name); description = TextFieldValue(it.description) }, onDelete = { vm.delete(it) }) }
        }
    }
}

@Composable
private fun CategoryItem(item: Category, onEdit: (Category) -> Unit, onDelete: (Category) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        Column(Modifier.padding(12.dp)) {
            Text(item.name, style = MaterialTheme.typography.titleMedium)
            if (item.description.isNotBlank()) { Spacer(Modifier.height(4.dp)); Text(item.description, style = MaterialTheme.typography.bodyMedium) }
            Spacer(Modifier.height(8.dp))
            Row { OutlinedButton(onClick = { onEdit(item) }) { Text("Editar") }; Spacer(Modifier.width(8.dp)); OutlinedButton(onClick = { onDelete(item) }) { Text("Excluir") } }
        }
    }
}

package com.biarapha.magicfolder.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.biarapha.magicfolder.data.Deck
import com.biarapha.magicfolder.data.MagicFolderDb
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DeckViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = MagicFolderDb.get(app).deckDao()

    val decks = dao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun save(id: Long, name: String, format: String) {
        viewModelScope.launch {
            if (id == 0L) dao.insert(Deck(name = name, format = format))
            else dao.update(Deck(id, name, format))
        }
    }
    fun delete(id: Long) = viewModelScope.launch { dao.deleteById(id) }
}

package com.biarapha.magicfolder.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.biarapha.magicfolder.data.Card
import com.biarapha.magicfolder.data.MagicFolderDb
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CardViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = MagicFolderDb.get(app).cardDao()

    val cards = dao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val wishlist = cards.map { it.filter { c -> c.inWishlist } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun observeById(id: Long) = dao.observeById(id)

    fun save(
        id: Long,
        name: String,
        manaCost: String,
        type: String,
        colors: String,
        inWishlist: Boolean
    ) {
        viewModelScope.launch {
            val c = Card(id, name, manaCost, type, colors, inWishlist)
            if (id == 0L) dao.insert(c) else dao.update(c)
        }
    }

    fun toggleWishlist(id: Long) = viewModelScope.launch { dao.toggleWishlist(id) }
    fun delete(id: Long) = viewModelScope.launch { dao.deleteById(id) }
}

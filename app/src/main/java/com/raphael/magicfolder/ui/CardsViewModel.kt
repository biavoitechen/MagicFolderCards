package com.raphael.magicfolder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raphael.magicfolder.data.local.entity.Card
import com.raphael.magicfolder.data.repository.CardRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CardsViewModel(private val repo: CardRepository) : ViewModel() {
    val cards = repo.observeAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    fun add(title: String, notes: String, categoryId: Long?) = viewModelScope.launch { repo.add(title, notes, categoryId) }
    fun update(card: Card) = viewModelScope.launch { repo.update(card) }
    fun delete(card: Card) = viewModelScope.launch { repo.delete(card) }
}

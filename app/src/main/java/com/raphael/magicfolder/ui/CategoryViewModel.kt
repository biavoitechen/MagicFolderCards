package com.raphael.magicfolder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raphael.magicfolder.data.local.entity.Category
import com.raphael.magicfolder.data.repository.CategoryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoryViewModel(private val repo: CategoryRepository) : ViewModel() {
    val categories = repo.observeAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    fun add(name: String, description: String) = viewModelScope.launch { repo.add(name, description) }
    fun update(category: Category) = viewModelScope.launch { repo.update(category) }
    fun delete(category: Category) = viewModelScope.launch { repo.delete(category) }
}

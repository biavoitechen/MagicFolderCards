package com.raphael.magicfolder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raphael.magicfolder.ServiceLocator
import com.raphael.magicfolder.data.repository.CardRepository
import com.raphael.magicfolder.data.repository.CategoryRepository

@Suppress("UNCHECKED_CAST")
class CardsVMFactory(private val repo: CardRepository = ServiceLocator.cardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = CardsViewModel(repo) as T
}

@Suppress("UNCHECKED_CAST")
class CategoryVMFactory(private val repo: CategoryRepository = ServiceLocator.categoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = CategoryViewModel(repo) as T
}

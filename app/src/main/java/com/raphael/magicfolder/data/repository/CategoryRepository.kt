package com.raphael.magicfolder.data.repository

import com.raphael.magicfolder.data.local.dao.CategoryDao
import com.raphael.magicfolder.data.local.entity.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val dao: CategoryDao) {
    fun observeAll(): Flow<List<Category>> = dao.observeAll()
    suspend fun get(id: Long) = dao.getById(id)
    suspend fun add(name: String, description: String) = dao.insert(Category(name = name, description = description))
    suspend fun update(category: Category) = dao.update(category)
    suspend fun delete(category: Category) = dao.delete(category)
}

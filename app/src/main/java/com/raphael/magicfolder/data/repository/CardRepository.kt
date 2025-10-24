package com.raphael.magicfolder.data.repository

import com.raphael.magicfolder.data.local.dao.CardDao
import com.raphael.magicfolder.data.local.entity.Card
import kotlinx.coroutines.flow.Flow

class CardRepository(private val dao: CardDao) {
    fun observeAll(): Flow<List<Card>> = dao.observeAll()
    suspend fun add(title: String, notes: String, categoryId: Long?) =
        dao.insert(Card(title = title, notes = notes, categoryId = categoryId))
    suspend fun update(card: Card) = dao.update(card)
    suspend fun delete(card: Card) = dao.delete(card)
}

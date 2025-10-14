package com.biarapha.magicfolder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao {
    @Query("SELECT * FROM decks ORDER BY name")
    fun getAll(): Flow<List<Deck>>

    @Query("SELECT * FROM decks WHERE id = :id")
    suspend fun getById(id: Long): Deck?

    @Insert suspend fun insert(deck: Deck): Long
    @Update suspend fun update(deck: Deck)
    @Delete suspend fun delete(deck: Deck)

    @Query("DELETE FROM decks WHERE id = :id")
    suspend fun deleteById(id: Long)
}

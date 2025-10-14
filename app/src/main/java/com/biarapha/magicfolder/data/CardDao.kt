package com.biarapha.magicfolder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM cards ORDER BY id DESC")
    fun getAll(): Flow<List<Card>>

    @Query("SELECT * FROM cards WHERE id = :id LIMIT 1")
    fun observeById(id: Long): Flow<Card?>

    @Query("SELECT * FROM cards WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): Card?

    @Insert suspend fun insert(card: Card): Long
    @Update suspend fun update(card: Card)

    @Query("DELETE FROM cards WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("""
        UPDATE cards
        SET inWishlist = CASE inWishlist WHEN 0 THEN 1 ELSE 0 END
        WHERE id = :id
    """)
    suspend fun toggleWishlist(id: Long)
}

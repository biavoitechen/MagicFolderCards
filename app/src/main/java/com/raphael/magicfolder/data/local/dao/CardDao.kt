package com.raphael.magicfolder.data.local.dao

import androidx.room.*
import com.raphael.magicfolder.data.local.entity.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM cards ORDER BY id DESC")
    fun observeAll(): Flow<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(card: Card): Long
    @Update suspend fun update(card: Card)
    @Delete suspend fun delete(card: Card)
}

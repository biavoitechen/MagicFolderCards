package com.raphael.magicfolder.data.local.dao

import androidx.room.*
import com.raphael.magicfolder.data.local.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun observeAll(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getById(id: Long): Category?

    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(category: Category): Long
    @Update suspend fun update(category: Category)
    @Delete suspend fun delete(category: Category)
}

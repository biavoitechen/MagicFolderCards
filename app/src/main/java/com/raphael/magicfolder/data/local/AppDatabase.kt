package com.raphael.magicfolder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.raphael.magicfolder.data.local.dao.CardDao
import com.raphael.magicfolder.data.local.dao.CategoryDao
import com.raphael.magicfolder.data.local.entity.Card
import com.raphael.magicfolder.data.local.entity.Category

@Database(entities = [Card::class, Category::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `categories`(
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `name` TEXT NOT NULL,
                        `description` TEXT NOT NULL
                    )
                """.trimIndent())
            }
        }
    }
}

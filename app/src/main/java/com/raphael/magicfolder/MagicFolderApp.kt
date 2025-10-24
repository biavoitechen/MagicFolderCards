package com.raphael.magicfolder

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.raphael.magicfolder.data.local.AppDatabase
import com.raphael.magicfolder.data.repository.CardRepository
import com.raphael.magicfolder.data.repository.CategoryRepository

class MagicFolderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }
}

object ServiceLocator {
    private lateinit var appContext: Context
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(appContext, AppDatabase::class.java, "magicfolder.db")
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
    }

    val cardRepository: CardRepository by lazy { CardRepository(db.cardDao()) }
    val categoryRepository: CategoryRepository by lazy { CategoryRepository(db.categoryDao()) }

    fun init(context: Context) { appContext = context.applicationContext }
}

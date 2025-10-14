package com.biarapha.magicfolder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val manaCost: String,
    val type: String = "",
    val colors: String = "",
    val inWishlist: Boolean = false,
    val notes: String? = null
)

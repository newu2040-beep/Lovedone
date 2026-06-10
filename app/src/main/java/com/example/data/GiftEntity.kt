package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "gifts")
data class GiftEntity(
    @PrimaryKey val id: String,
    val type: String,
    val recipientName: String,
    val message: String,
    val theme: String,
    val unlockType: String,
    val unlockSecret: String,
    val createdAt: Long = System.currentTimeMillis()
)

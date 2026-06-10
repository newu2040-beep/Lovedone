package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GiftEntity::class], version = 1, exportSchema = false)
abstract class LovedOneDatabase : RoomDatabase() {
    abstract fun giftDao(): GiftDao
}

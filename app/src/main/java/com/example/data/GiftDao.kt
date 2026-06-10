package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GiftDao {
    @Query("SELECT * FROM gifts ORDER BY createdAt DESC")
    fun getAllGifts(): Flow<List<GiftEntity>>

    @Query("SELECT * FROM gifts WHERE id = :id LIMIT 1")
    fun getGiftById(id: String): Flow<GiftEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGift(gift: GiftEntity)

    @Query("DELETE FROM gifts WHERE id = :id")
    suspend fun deleteGiftById(id: String)
}

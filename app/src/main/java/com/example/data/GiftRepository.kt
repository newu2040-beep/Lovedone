package com.example.data

import kotlinx.coroutines.flow.Flow

class GiftRepository(private val giftDao: GiftDao) {
    val allGifts: Flow<List<GiftEntity>> = giftDao.getAllGifts()

    fun getGiftById(id: String): Flow<GiftEntity?> {
        return giftDao.getGiftById(id)
    }

    suspend fun insert(gift: GiftEntity) {
        giftDao.insertGift(gift)
    }

    suspend fun deleteById(id: String) {
        giftDao.deleteGiftById(id)
    }
}

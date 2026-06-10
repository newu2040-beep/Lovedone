package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai.GeminiHelper
import com.example.data.GiftEntity
import com.example.data.GiftRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class LovedOneViewModel(
    private val repository: GiftRepository,
    private val geminiHelper: GeminiHelper = GeminiHelper()
) : ViewModel() {

    val uiState: StateFlow<List<GiftEntity>> = repository.allGifts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun createGift(
        type: String,
        recipientName: String,
        message: String,
        theme: String,
        unlockType: String,
        unlockSecret: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            val gift = GiftEntity(
                id = UUID.randomUUID().toString(),
                type = type,
                recipientName = recipientName,
                message = message,
                theme = theme,
                unlockType = unlockType,
                unlockSecret = unlockSecret
            )
            repository.insert(gift)
            onSuccess()
        }
    }

    // AI generation state
    var generatedMessage: String = ""
        private set
    var isGenerating: Boolean = false
        private set

    fun generateMessage(recipientName: String, occasion: String, context: String, onUpdate: (String) -> Unit) {
        viewModelScope.launch {
            isGenerating = true
            onUpdate("Generating...")
            val result = geminiHelper.generateGreeting(recipientName, occasion, context)
            generatedMessage = result
            isGenerating = false
            onUpdate(result)
        }
    }
}

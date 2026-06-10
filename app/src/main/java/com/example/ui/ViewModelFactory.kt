package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.GiftRepository

class LovedOneViewModelFactory(
    private val repository: GiftRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LovedOneViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LovedOneViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

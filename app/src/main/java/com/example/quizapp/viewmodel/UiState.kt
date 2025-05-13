package com.example.quizapp.viewmodel

import com.example.quizapp.data.model.Question

sealed class UiState {
    object Loading : UiState()
    data class Success(val questions: List<Question>) : UiState()
    object Finished : UiState()
    data class Error(val message: String) : UiState()
}

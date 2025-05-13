package com.example.quizapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.*
import com.example.quizapp.data.model.Question
import com.example.quizapp.data.remote.ApiClient
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    var currentIndex by mutableStateOf(0)
        private set

    var score by mutableStateOf(0)
        private set

    val questions: List<Question>
        get() = (uiState as? UiState.Success)?.questions ?: emptyList()

    val currentQuestion: Question?
        get() = questions.getOrNull(currentIndex)

    init {
        fetchQuestions()
    }

    fun fetchQuestions() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getQuestions()
                uiState = UiState.Success(response.results)
                currentIndex = 0
                score = 0
            } catch (e: Exception) {
                uiState = UiState.Error("Failed to load quiz: ${e.message}")
            }
        }
    }

    // ✅ Just checks the answer — doesn't move forward
    fun checkAnswer(answer: String) {
        val current = currentQuestion ?: return
        if (answer == current.correct_answer) score++
    }

    // ✅ Called from QuizScreen after showing answer feedback
    fun nextQuestionOrFinish() {
        currentIndex++
        if (currentIndex >= questions.size) {
            uiState = UiState.Finished
        }
    }

    fun restartQuiz() {
        fetchQuestions()
    }
}

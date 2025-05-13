package com.example.quizapp.data.remote

import com.example.quizapp.data.model.QuizResponse
import retrofit2.http.GET

interface QuizApiService {
    @GET("api.php?amount=10&category=18&difficulty=easy&type=multiple")
    suspend fun getQuestions(): QuizResponse
}

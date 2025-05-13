package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quizapp.viewmodel.QuizViewModel
import com.example.quizapp.viewmodel.UiState
import com.example.quizapp.ui.components.TopBarWithBackButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.Timestamp
import kotlinx.coroutines.delay

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel = viewModel()
) {
    var submitted by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBarWithBackButton(navController = navController, title = "Quiz")
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val state = viewModel.uiState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("⚠️ ${state.message}", color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.restartQuiz() }) {
                            Text("Retry")
                        }
                    }
                }

                is UiState.Finished -> {
                    LaunchedEffect(Unit) {
                        if (!submitted) {
                            submitted = true
                            val user = FirebaseAuth.getInstance().currentUser

                            user?.let {
                                val username = it.displayName ?: it.email ?: "Unknown"
                                val uid = it.uid

                                Firebase.firestore.collection("leaderboard")
                                    .document(uid)
                                    .set(
                                        mapOf(
                                            "username" to username,
                                            "score" to viewModel.score,
                                            "timestamp" to Timestamp.now()
                                        )
                                    )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("🎉 Quiz Completed!", style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Score: ${viewModel.score}", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(onClick = { viewModel.restartQuiz() }) {
                            Text("Restart Quiz")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { navController.navigate("leaderboard") }) {
                            Text("View Leaderboard")
                        }
                    }
                }

                is UiState.Success -> {
                    val question = viewModel.currentQuestion
                    var selectedAnswer by remember(question) { mutableStateOf<String?>(null) }

                    if (question != null) {
                        val options = remember(question) {
                            (question.incorrect_answers + question.correct_answer).shuffled()
                        }

                        LaunchedEffect(selectedAnswer) {
                            if (selectedAnswer != null) {
                                delay(1500)
                                selectedAnswer = null
                                viewModel.nextQuestionOrFinish()
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = question.question,
                                style = MaterialTheme.typography.titleLarge
                            )

                            options.forEach { option ->
                                val isCorrect = option == question.correct_answer
                                val isSelected = selectedAnswer == option
                                val hasAnswered = selectedAnswer != null

                                val backgroundColor = when {
                                    !hasAnswered -> MaterialTheme.colorScheme.surface
                                    isCorrect -> MaterialTheme.colorScheme.tertiaryContainer
                                    isSelected -> MaterialTheme.colorScheme.errorContainer
                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                }

                                val icon = when {
                                    hasAnswered && isCorrect -> Icons.Filled.Check
                                    hasAnswered && isSelected && !isCorrect -> Icons.Filled.Close
                                    else -> null
                                }

                                Button(
                                    onClick = {
                                        if (selectedAnswer == null) {
                                            selectedAnswer = option
                                            viewModel.checkAnswer(option)
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(option, color = Color.White)
                                        icon?.let {
                                            Icon(
                                                imageVector = it,
                                                contentDescription = if (it == Icons.Filled.Check) "Correct" else "Incorrect",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Score: ${viewModel.score}")
                        }
                    }
                }
            }
        }
    }
}

package com.example.quizapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class AuthViewModel : ViewModel() {
    var isLoggedIn by mutableStateOf(false)
        private set

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signup(
        email: String,
        password: String,
        username: String,
        context: Context,
        onSuccess: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                // ✅ Set displayName (username)
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .build()

                result.user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { profileUpdateTask ->
                        if (profileUpdateTask.isSuccessful) {
                            isLoggedIn = true
                            onSuccess()
                        } else {
                            val msg = profileUpdateTask.exception?.localizedMessage ?: "Profile update failed"
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            Log.e("AuthViewModel", "Profile update error", profileUpdateTask.exception)
                        }
                    }
            }
            .addOnFailureListener { exception ->
                val errorMessage = exception.localizedMessage ?: "Signup failed"
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("AuthViewModel", "Signup error", exception)
            }
    }

    fun login(email: String, password: String, context: Context, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoggedIn = task.isSuccessful
                if (task.isSuccessful) {
                    onResult(true)
                } else {
                    val errorMessage = task.exception?.localizedMessage ?: "Login failed"
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("AuthViewModel", "Login error", task.exception)
                    onResult(false)
                }
            }
    }
}

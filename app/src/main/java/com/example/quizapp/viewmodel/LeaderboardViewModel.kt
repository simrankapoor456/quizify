package com.example.quizapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class LeaderboardEntry(
    val uid: String,
    val username: String,
    val score: Int
)

class LeaderboardViewModel : ViewModel() {

    private val _leaderboard = MutableStateFlow<List<LeaderboardEntry>>(emptyList())
    val leaderboard: StateFlow<List<LeaderboardEntry>> = _leaderboard

    private var listener: ListenerRegistration? = null

    init {
        fetchLeaderboard()
    }

    private fun fetchLeaderboard() {
        listener = Firebase.firestore.collection("leaderboard")
            .orderBy("score", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("LeaderboardViewModel", "Realtime fetch failed: ${error.message}", error)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val list = snapshot.documents.mapNotNull { document ->
                        val uid = document.id
                        val username = document.getString("username")
                        val score = document.getLong("score")?.toInt()

                        if (username != null && score != null) {
                            LeaderboardEntry(uid, username, score)
                        } else {
                            Log.w("LeaderboardViewModel", "Skipping malformed entry: ${document.id}")
                            null
                        }
                    }
                    _leaderboard.value = list
                    Log.d("LeaderboardViewModel", "Realtime leaderboard: ${list.size} entries")
                } else {
                    _leaderboard.value = emptyList()
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }
}

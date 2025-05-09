
# Quizify - Kotlin Android Quiz App

## 📱 Overview
**Quizify** is a real-time quiz game app developed in Kotlin using modern Android practices (Jetpack Compose + MVVM). It challenges users with multiple-choice questions, tracks scores, supports Firebase authentication, and ranks users on a leaderboard.

## ✨ Features
- User authentication (Login/Signup with Firebase)
- Quiz with multiple topics and real-time scoring
- Leaderboard with top players
- Password change functionality
- Clean UI with Jetpack Compose

## 🧠 Functional Programming Concepts Used
This app demonstrates the following Kotlin functional programming features:
1. **Immutability** with `val`
2. **Lambda expressions** and **higher-order functions** (e.g., `map`, click listeners)
3. **Safe calls** (`?.`) and **Elvis operator** (`?:`)
4. **Control flow**: `when`, `if/else`
5. **Data classes** with `copy()` and equality
6. **Exception handling** with `try`/`catch`
7. **Collections** like `List`, `Map`
8. **Variance and generics** (e.g., `<T : Comparable<T>>`, `in` keyword)

## 🏗️ Architecture
- Follows the **MVVM** design pattern
- Jetpack Compose UI in `ui/screens`
- ViewModels manage state and logic using `LiveData` or `State`
- Remote API access via `QuizApiService`
- Navigation handled with Compose's `NavHost`

## 🔧 Setup Instructions
1. Clone or unzip the project.
2. Open in **Android Studio Hedgehog+**.
3. Make sure your **emulator or physical device** is running Android 10+.
4. Add your Firebase project’s `google-services.json` file in `app/` if not already present.
5. Click `Run` to build and launch the app.

## 🔌 Dependencies
- Jetpack Compose
- Firebase Authentication
- Kotlin Coroutines
- Retrofit (for API calls)

## 👥 Team Contributions
- *Anavi Reddyreddy:*
Team Lead, Firebase, Data Modeling, UI/UX, Testing, Presentation
- *Simran Kapoor:*
API Integration, Version Control, Data Modeling, UI/UX, Testing, Presentation
- *Monish Patalay:*
Firebase, Authentication, Data Modeling, UI/UX, Testing, Presentation
- *Samprat Sakhare:*
API Integration, Authentication, UI/UX, Testing, Presentation
*Each member contributed to design, implementation, and debugging.*

## 📹 Demo Video
*LINK*

## 📄 References
- [Firebase Documentation](https://firebase.google.com/docs)
- Jetpack Compose Samples (by Google)
- Assistance from ChatGPT and tutorial references are cited where used.


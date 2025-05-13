# **Quizify – Kotlin Android Quiz App**

## 📱 Overview

**Quizify** is a real-time quiz game app built with Kotlin using modern Android development practices like Jetpack Compose and the MVVM architecture. It offers engaging multiple-choice questions, tracks scores, authenticates users via Firebase, and features a competitive leaderboard.

## ✨ Features

* 🔐 **User Authentication** (Login/Signup via Firebase)
* ❓ **Interactive Quizzes** with real-time scoring
* 🏆 **Leaderboard** to track top players
* 🔁 **Password Management** (change/reset)
* 🎨 **Modern UI** using Jetpack Compose

## 🧠 Functional Programming Concepts Used

This app demonstrates the following Kotlin functional programming features:

1. ✅ **Immutability** using `val`
2. ✅ **Lambda expressions** and **higher-order functions** (`map`, `click listeners`)
3. ✅ **Safe calls** (`?.`) and **Elvis operator** (`?:`)
4. ✅ **Control flow**: `when`, `if/else`
5. ✅ **Data classes** with `copy()` and object equality
6. ✅ **Exception handling** using `try`/`catch`
7. ✅ **Collections** such as `List` and `Map`

## 🏗️ App Architecture

* 🧱 **Pattern:** MVVM (Model-View-ViewModel)
* 🖼️ **UI Layer:** Built entirely with **Jetpack Compose** in `ui/screens/`
* 🧠 **ViewModels:** Handle logic and UI state using `mutableStateOf`, `StateFlow`, or `LiveData`
* 🌐 **API Service:** Retrofit used for fetching quiz questions
* 🔀 **Navigation:** Handled with Jetpack Compose’s `NavHost`

## 🌐 API Used

* 📡 [**Open Trivia DB**](https://opentdb.com/) – Used for fetching quiz questions dynamically.

## 🔧 Setup Instructions

1. Clone or unzip the project.
2. Open in **Android Studio (Hedgehog or newer)**.
3. Ensure your **emulator or device runs Android 10+**.
4. If not already present, add your Firebase project's `google-services.json` under `app/`.
5. Click **Run** to build and launch the app.

## 🔌 Dependencies

* 🧩 Jetpack Compose
* 🔐 Firebase Authentication
* 🔄 Kotlin Coroutines
* 🌐 Retrofit (for REST API)

## 👥 Team Contributions

| Name                 | Roles                                                                 |
| -------------------- | ----------------------------------------------------------------------|
| **Anavi Reddyreddy** | Team Lead, Firebase, Data Modeling, UI/UX, Documentation              |
| **Simran Kapoor**    | API Integration, Version Control, Data Modeling, UI/UX, Documentation |
| **Monish Patalay**   | Firebase, Authentication, Data Modeling, UI/UX, Documentation         |
| **Samprat Sakhare**  | API Integration, Authentication, UI/UX, Documentation                 |

> *All members actively contributed to design, implementation, testing, and debugging.*

## 📹 Demo Video

[**Watch Demo Video**](https://drive.google.com/file/d/17loUa7O5Szv0ldFrYc9uypZCzkbV0P2K/view)

## 📄 References

* 📚 [Firebase Documentation](https://firebase.google.com/docs)
* 🎨 Jetpack Compose Samples (Google)
* 🌐 [Open Trivia DB](https://opentdb.com/)
* 💬 Conceptual assistance from ChatGPT
* 🎥 Tutorials and official docs (properly cited in code/comments if used)

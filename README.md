# Karya - To-Do List App

## 📌 Project Overview

Karya is a sleek and efficient offline To-Do List app designed to help users manage daily tasks seamlessly. 
It ensures task persistence using Room Database and features a minimalistic UI.

## 🚀 Features

- **Task Management**: Add, edit, delete, and mark tasks as completed or pending.
- **Offline Support**: All tasks are stored locally using Room Database.
- **Dark Mode**: Supports automatic theme switching based on system settings.
- **MVVM Architecture**: Ensures clean code separation and maintainability.
- **State Management**: Uses StateFlow or LiveData for UI updates.

## 🛠 Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Database
- **Dependency Injection**: Hilt
- **State Management**: StateFlow / LiveData

## 📂 Project Structure

```
Karya/
│── app/
│   ├── data/            # Room Database setup (Entities, DAO, Repository)
│   ├── viewmodel/       # MVVM ViewModels
│   ├── di/              # Hilt Dependency Injection
│   ├── navigation/      # Navigation setup
│   ├── utils/           # Utility classes and extensions
│   ├── MainActivity.kt  # Entry point of the app
```

🔹 **Karya - Simplify Your Tasks, Enhance Your Productivity!**


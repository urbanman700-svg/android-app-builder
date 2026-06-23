# Android App Builder Studio

A lightweight, Android Studio-like IDE built natively for Android devices, optimized for low-end devices. Create, develop, and build Android projects directly on your Android phone without needing a PC.

## Features

✨ **Project Management**
- Create new Android projects from templates
- Organize multiple projects
- Quick project switching

🎨 **Template System**
- Blank Project
- Navigation Activity
- Bottom Navigation
- Tab Navigation
- Master-Detail
- Settings Screen

📝 **Code Editor**
- Kotlin syntax highlighting
- Real-time error checking
- Code completion suggestions
- Fast & lightweight editor

⚙️ **Android Project Generator**
- Auto-generate Android manifest
- Build configuration setup
- Resource file creation
- Package structure generation

💾 **Local Storage**
- Projects stored locally in device
- Room Database for project metadata
- File system for project files

📦 **Build Support**
- Gradle build file generation
- Dependency management
- APK export ready (future feature)

## Requirements

- Android 5.0+ (API 21+)
- Minimum 2GB RAM (Recommended 4GB+)
- 100MB free storage for projects

## Architecture

This app follows **MVVM (Model-View-ViewModel)** pattern with:
- **Jetpack Compose** for UI (lightweight and performant)
- **Room Database** for data persistence
- **Kotlin Coroutines** for async operations
- **Repository Pattern** for clean data access

## Getting Started

### Prerequisites
- Android Studio (for building)
- JDK 11 or higher
- Gradle 8.1+

### Build Instructions

```bash
# Clone the repository
git clone https://github.com/urbanman700-svg/android-app-builder.git
cd android-app-builder

# Build the project
./gradlew build

# Install on device/emulator
./gradlew installDebug
```

## Contributing

Contributions are welcome! Please fork and submit pull requests.

## License

MIT License - See LICENSE file for details

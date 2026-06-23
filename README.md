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

## Project Structure

```
android-app-builder/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── kotlin/com/urbanman700/appbuilder/
│   │   │   ├── MainActivity.kt
│   │   │   ├── ui/
│   │   │   │   ├── screens/
│   │   │   │   ├── components/
│   │   │   │   └── theme/
│   │   │   ├── viewmodel/
│   │   │   ├── data/
│   │   │   │   ├── models/
│   │   │   │   ├── repository/
│   │   │   │   └── local/
│   │   │   └── utils/
│   │   └── res/
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

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

# Run tests
./gradlew test
```

### Development

1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Run the app on an emulator or device

## Dependencies

**Core Android**
- androidx.core:core-ktx
- androidx.appcompat:appcompat
- androidx.lifecycle:lifecycle-runtime-ktx

**UI Framework**
- androidx.compose.ui
- androidx.compose.material3
- androidx.activity:activity-compose

**Data Persistence**
- androidx.room:room-runtime
- androidx.room:room-ktx

**Async Operations**
- org.jetbrains.kotlinx:kotlinx-coroutines-android

**Utilities**
- commons-io:commons-io
- com.squareup.moshi:moshi-kotlin

## Roadmap

- [ ] Advanced code editor with IntelliSense
- [ ] Project templates expansion
- [ ] XML layout preview
- [ ] APK export functionality
- [ ] GitHub integration
- [ ] Cloud project sync
- [ ] Collaborative editing
- [ ] Plugin system

## Performance Optimization for Low-End Devices

- Minimal UI layers with Compose
- Efficient database queries with Room
- Image caching and optimization
- Background processing with Coroutines
- ProGuard enabled for release builds
- Optimized dependencies (no heavyweight frameworks)

## Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

**urbanman700-svg** - Android Developer

## Acknowledgments

- Android Architecture Components
- Jetpack Compose team
- Kotlin community

## Support

For issues, questions, or suggestions:
- 🐛 Report bugs on [GitHub Issues](https://github.com/urbanman700-svg/android-app-builder/issues)
- 💬 Start a discussion on [GitHub Discussions](https://github.com/urbanman700-svg/android-app-builder/discussions)

---

**Made with ❤️ for Android developers**

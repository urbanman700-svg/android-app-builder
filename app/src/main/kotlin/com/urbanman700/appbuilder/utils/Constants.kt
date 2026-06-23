package com.urbanman700.appbuilder.utils

object Constants {
    const val DATABASE_NAME = "project_database"
    const val DEFAULT_MIN_SDK = 21
    const val DEFAULT_TARGET_SDK = 34

    object FileStructure {
        const val SRC_MAIN = "app/src/main"
        const val KOTLIN_DIR = "app/src/main/kotlin"
        const val RES_DIR = "app/src/main/res"
        const val LAYOUT_DIR = "app/src/main/res/layout"
        const val DRAWABLE_DIR = "app/src/main/res/drawable"
        const val VALUES_DIR = "app/src/main/res/values"
    }

    object DefaultDependencies {
        val CORE = listOf(
            "androidx.core:core-ktx:1.12.0",
            "androidx.appcompat:appcompat:1.6.1",
            "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
        )
        val COMPOSE = listOf(
            "androidx.compose.ui:ui:1.5.4",
            "androidx.compose.material3:material3:1.1.2",
            "androidx.activity:activity-compose:1.8.0"
        )
    }
}

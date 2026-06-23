package com.urbanman700.appbuilder.utils

import android.content.Context
import java.io.File

class FileManager(private val context: Context) {
    private val projectsDir = File(context.filesDir, "projects")

    init {
        if (!projectsDir.exists()) {
            projectsDir.mkdirs()
        }
    }

    fun createProjectDirectory(projectName: String): File {
        val projectDir = File(projectsDir, projectName)
        projectDir.mkdirs()

        // Create basic structure
        File(projectDir, "app/src/main/kotlin").mkdirs()
        File(projectDir, "app/src/main/res/layout").mkdirs()
        File(projectDir, "app/src/main/res/values").mkdirs()
        File(projectDir, "app/src/main/res/drawable").mkdirs()

        return projectDir
    }

    fun writeFile(projectDir: File, filePath: String, content: String) {
        val file = File(projectDir, filePath)
        file.parentFile?.mkdirs()
        file.writeText(content)
    }

    fun readFile(projectDir: File, filePath: String): String? {
        val file = File(projectDir, filePath)
        return if (file.exists()) file.readText() else null
    }

    fun deleteProject(projectName: String) {
        val projectDir = File(projectsDir, projectName)
        projectDir.deleteRecursively()
    }

    fun getProjectDirectory(projectName: String): File {
        return File(projectsDir, projectName)
    }
}

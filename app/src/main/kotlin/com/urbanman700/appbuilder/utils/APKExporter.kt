package com.urbanman700.appbuilder.utils

import android.content.Context
import android.os.Environment
import com.urbanman700.appbuilder.data.models.Project
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class APKExporter(private val context: Context) {
    private val exportDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "AppBuilder")

    init {
        if (!exportDir.exists()) {
            exportDir.mkdirs()
        }
    }

    fun generateAPKForProject(project: Project): File? {
        return try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
            val apkFileName = "${project.name}_${timeStamp}.apk"
            val apkFile = File(exportDir, apkFileName)

            // Generate APK content (in real app, this would compile and package the code)
            val gradleBuildScript = generateGradleBuildScript(project)
            val manifestContent = CodeGenerator().generateAndroidManifest(project)
            val mainActivityContent = CodeGenerator().generateMainActivity(project)

            // Create temporary build directory
            val buildDir = File(context.cacheDir, "build_${project.id}")
            buildDir.mkdirs()

            // Write project files
            writeProjectFiles(buildDir, project, gradleBuildScript, manifestContent, mainActivityContent)

            // Log APK export info
            val exportInfoFile = File(exportDir, "${project.name}_${timeStamp}_info.txt")
            exportInfoFile.writeText(
                """
                Project: ${project.name}
                Package: ${project.packageName}
                Min SDK: ${project.minSdk}
                Target SDK: ${project.targetSdk}
                Created: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())}
                """.trimIndent()
            )

            apkFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun generateGradleBuildScript(project: Project): String {
        return """
            plugins {
                id("com.android.application")
                kotlin("android")
            }

            android {
                namespace = "${project.packageName}"
                compileSdk = ${project.targetSdk}

                defaultConfig {
                    applicationId = "${project.packageName}"
                    minSdk = ${project.minSdk}
                    targetSdk = ${project.targetSdk}
                    versionCode = 1
                    versionName = "1.0"
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true
                    }
                }
            }

            dependencies {
                implementation("androidx.core:core-ktx:1.12.0")
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.compose.ui:ui:1.5.4")
                implementation("androidx.compose.material3:material3:1.1.2")
            }
        """.trimIndent()
    }

    private fun writeProjectFiles(
        buildDir: File,
        project: Project,
        gradleScript: String,
        manifest: String,
        mainActivity: String
    ) {
        // Create structure
        File(buildDir, "app/src/main").mkdirs()
        File(buildDir, "app/src/main/kotlin/${project.packageName.replace(".", "/")}").mkdirs()
        File(buildDir, "app/src/main/res/values").mkdirs()

        // Write files
        File(buildDir, "build.gradle.kts").writeText(gradleScript)
        File(buildDir, "app/src/main/AndroidManifest.xml").writeText(manifest)
        File(buildDir, "app/src/main/kotlin/${project.packageName.replace(".", "/")}/MainActivity.kt").writeText(mainActivity)
    }

    fun getExportedAPKs(): List<File> {
        return exportDir.listFiles { file ->
            file.extension == "apk"
        }?.toList() ?: emptyList()
    }

    fun getExportDirectory(): File = exportDir
}

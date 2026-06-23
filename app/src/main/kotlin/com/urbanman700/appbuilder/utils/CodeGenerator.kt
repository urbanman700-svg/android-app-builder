package com.urbanman700.appbuilder.utils

import com.urbanman700.appbuilder.data.models.Project

class CodeGenerator {
    fun generateMainActivity(project: Project): String {
        return """
            package ${project.packageName}

            import android.os.Bundle
            import androidx.appcompat.app.AppCompatActivity

            class MainActivity : AppCompatActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.activity_main)
                }
            }
        """.trimIndent()
    }

    fun generateAndroidManifest(project: Project): String {
        return """
            <?xml version="1.0" encoding="utf-8"?>
            <manifest xmlns:android="http://schemas.android.com/apk/res/android"
                package="${project.packageName}">

                <uses-permission android:name="android.permission.INTERNET" />

                <application
                    android:allowBackup="true"
                    android:icon="@mipmap/ic_launcher"
                    android:label="@string/app_name"
                    android:supportsRtl="true"
                    android:theme="@style/Theme.AppBuilder">

                    <activity
                        android:name=".MainActivity"
                        android:exported="true">
                        <intent-filter>
                            <action android:name="android.intent.action.MAIN" />
                            <category android:name="android.intent.category.LAUNCHER" />
                        </intent-filter>
                    </activity>
                </application>
            </manifest>
        """.trimIndent()
    }

    fun generateBuildGradle(project: Project): String {
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
                        isMinifyEnabled = false
                    }
                }
            }

            dependencies {
                implementation("androidx.core:core-ktx:1.12.0")
                implementation("androidx.appcompat:appcompat:1.6.1")
            }
        """.trimIndent()
    }
}

package com.urbanman700.appbuilder.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val packageName: String,
    val templateId: String,
    val createdAt: Long = System.currentTimeMillis(),
    val modifiedAt: Long = System.currentTimeMillis(),
    val projectPath: String,
    val minSdk: Int = 21,
    val targetSdk: Int = 34
) : Serializable

package com.urbanman700.appbuilder.data.models

import java.io.Serializable

data class Template(
    val id: String,
    val name: String,
    val description: String,
    val icon: Int = 0,
    val category: TemplateCategory,
    val structure: ProjectStructure
) : Serializable

enum class TemplateCategory {
    BLANK, NAVIGATION, BOTTOM_NAV, TAB_NAV, MASTER_DETAIL, SETTINGS
}

data class ProjectStructure(
    val mainActivity: String,
    val layoutFiles: List<String>,
    val resources: List<String>,
    val dependencies: List<String>
) : Serializable

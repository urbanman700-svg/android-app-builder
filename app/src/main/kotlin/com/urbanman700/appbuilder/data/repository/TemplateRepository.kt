package com.urbanman700.appbuilder.data.repository

import com.urbanman700.appbuilder.data.models.ProjectStructure
import com.urbanman700.appbuilder.data.models.Template
import com.urbanman700.appbuilder.data.models.TemplateCategory

class TemplateRepository {
    fun getAvailableTemplates(): List<Template> {
        return listOf(
            Template(
                id = "blank",
                name = "Blank Project",
                description = "Start with an empty Android project",
                category = TemplateCategory.BLANK,
                structure = ProjectStructure(
                    mainActivity = "MainActivity.kt",
                    layoutFiles = listOf("activity_main.xml"),
                    resources = listOf("strings.xml", "colors.xml"),
                    dependencies = listOf(
                        "androidx.core:core-ktx",
                        "androidx.appcompat:appcompat"
                    )
                )
            ),
            Template(
                id = "navigation",
                name = "Navigation Drawer",
                description = "Project with navigation drawer implementation",
                category = TemplateCategory.NAVIGATION,
                structure = ProjectStructure(
                    mainActivity = "MainActivity.kt",
                    layoutFiles = listOf("activity_main.xml", "nav_header.xml"),
                    resources = listOf("strings.xml", "colors.xml", "menus.xml"),
                    dependencies = listOf(
                        "androidx.navigation:navigation-ui-ktx",
                        "androidx.drawerlayout:drawerlayout"
                    )
                )
            ),
            Template(
                id = "bottom_nav",
                name = "Bottom Navigation",
                description = "Project with bottom navigation bar",
                category = TemplateCategory.BOTTOM_NAV,
                structure = ProjectStructure(
                    mainActivity = "MainActivity.kt",
                    layoutFiles = listOf("activity_main.xml"),
                    resources = listOf("strings.xml", "colors.xml"),
                    dependencies = listOf(
                        "androidx.compose.material3:material3"
                    )
                )
            ),
            Template(
                id = "tab_nav",
                name = "Tab Navigation",
                description = "Project with tab-based navigation",
                category = TemplateCategory.TAB_NAV,
                structure = ProjectStructure(
                    mainActivity = "MainActivity.kt",
                    layoutFiles = listOf("activity_main.xml"),
                    resources = listOf("strings.xml", "colors.xml"),
                    dependencies = listOf(
                        "androidx.viewpager2:viewpager2"
                    )
                )
            )
        )
    }

    fun getTemplateById(templateId: String): Template? {
        return getAvailableTemplates().find { it.id == templateId }
    }
}

package com.urbanman700.appbuilder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateSelectionScreen(
    onTemplateSelected: (String) -> Unit,
    onBack: () -> Unit
) {
    val templates = listOf(
        TemplateItem(
            id = "blank",
            name = "Blank Project",
            description = "Start with an empty Android project",
            category = "Basic"
        ),
        TemplateItem(
            id = "navigation",
            name = "Navigation Drawer",
            description = "Project with navigation drawer implementation",
            category = "Navigation"
        ),
        TemplateItem(
            id = "bottom_nav",
            name = "Bottom Navigation",
            description = "Project with bottom navigation bar",
            category = "Navigation"
        ),
        TemplateItem(
            id = "tab_nav",
            name = "Tab Navigation",
            description = "Project with tab-based navigation",
            category = "Navigation"
        ),
        TemplateItem(
            id = "master_detail",
            name = "Master-Detail",
            description = "Master-detail list interface",
            category = "Layout"
        ),
        TemplateItem(
            id = "settings",
            name = "Settings Screen",
            description = "Pre-configured settings screen",
            category = "Screens"
        )
    )

    var selectedTemplate by remember { mutableStateOf<String?>(null) }
    var selectedCategory by remember { mutableIntStateOf(0) }

    val categories = listOf("All", "Basic", "Navigation", "Layout", "Screens")
    val filteredTemplates = if (selectedCategory == 0) {
        templates
    } else {
        templates.filter { it.category == categories[selectedCategory] }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Select Template") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Category Tabs
            TabRow(
                selectedTabIndex = selectedCategory,
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEachIndexed { index, category ->
                    Tab(
                        selected = selectedCategory == index,
                        onClick = { selectedCategory = index },
                        text = { Text(category, style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }

            // Templates List
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredTemplates) { template ->
                    TemplateCardItem(
                        template = template,
                        isSelected = selectedTemplate == template.id,
                        onSelect = { selectedTemplate = template.id }
                    )
                }
            }

            // Action Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        selectedTemplate?.let { onTemplateSelected(it) }
                    },
                    enabled = selectedTemplate != null,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Continue")
                }
            }
        }
    }
}

@Composable
fun TemplateCardItem(
    template: TemplateItem,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isSelected) {
                    Modifier.background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.medium
                    )
                } else {
                    Modifier
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        onClick = onSelect
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = template.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = template.description,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = template.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

data class TemplateItem(
    val id: String,
    val name: String,
    val description: String,
    val category: String
)

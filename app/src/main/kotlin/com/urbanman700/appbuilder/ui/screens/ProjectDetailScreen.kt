package com.urbanman700.appbuilder.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(
    projectName: String,
    packageName: String,
    createdDate: Long,
    onBack: () -> Unit,
    onEditCode: () -> Unit,
    onSettings: () -> Unit,
    onPreview: () -> Unit
) {
    val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.US)
    val createdDateStr = dateFormat.format(Date(createdDate))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(projectName) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Project Header Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = projectName,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = packageName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Created: $createdDateStr",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Quick Actions
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Button(
                    onClick = onEditCode,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Edit Code")
                }

                Button(
                    onClick = onPreview,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Folder,
                        contentDescription = "Preview",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Preview & Build")
                }

                OutlinedButton(
                    onClick = onSettings,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Project Settings")
                }
            }

            // Project Info Cards
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Project Information",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                ProjectInfoItem(
                    label = "Min SDK",
                    value = "21 (Android 5.0)"
                )
                ProjectInfoItem(
                    label = "Target SDK",
                    value = "34 (Android 14)"
                )
                ProjectInfoItem(
                    label = "Project Type",
                    value = "Android App"
                )
                ProjectInfoItem(
                    label = "Language",
                    value = "Kotlin"
                )
            }
        }
    }
}

@Composable
fun ProjectInfoItem(
    label: String,
    value: String
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

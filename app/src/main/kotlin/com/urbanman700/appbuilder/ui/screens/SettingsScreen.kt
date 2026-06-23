package com.urbanman700.appbuilder.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    projectName: String,
    onBack: () -> Unit,
    onDeleteProject: () -> Unit
) {
    var appName by remember { mutableStateOf(projectName) }
    var packageName by remember { mutableStateOf("com.example.$projectName") }
    var minSdkVersion by remember { mutableStateOf("21") }
    var targetSdkVersion by remember { mutableStateOf("34") }
    var enableAnalytics by remember { mutableStateOf(true) }
    var enableCrashReporting by remember { mutableStateOf(true) }
    var autoBackup by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Project Settings") },
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // App Information Section
            SettingsSectionHeader("App Information")

            OutlinedTextField(
                value = appName,
                onValueChange = { appName = it },
                label = { Text("App Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = packageName,
                onValueChange = { packageName = it },
                label = { Text("Package Name") },
                modifier = Modifier.fillMaxWidth()
            )

            // SDK Configuration Section
            SettingsSectionHeader("SDK Configuration")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = minSdkVersion,
                    onValueChange = { minSdkVersion = it },
                    label = { Text("Min SDK") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = targetSdkVersion,
                    onValueChange = { targetSdkVersion = it },
                    label = { Text("Target SDK") },
                    modifier = Modifier.weight(1f)
                )
            }

            Card {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Min SDK Version: $minSdkVersion",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "Target SDK Version: $targetSdkVersion",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "Supports Android 5.0 and above",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Features Section
            SettingsSectionHeader("Features")

            SettingToggleItem(
                title = "Analytics",
                description = "Enable usage analytics",
                checked = enableAnalytics,
                onCheckedChange = { enableAnalytics = it },
                icon = Icons.Default.NotificationsActive
            )

            SettingToggleItem(
                title = "Crash Reporting",
                description = "Report crashes to Firebase",
                checked = enableCrashReporting,
                onCheckedChange = { enableCrashReporting = it },
                icon = Icons.Default.Build
            )

            SettingToggleItem(
                title = "Auto Backup",
                description = "Backup app data to cloud",
                checked = autoBackup,
                onCheckedChange = { autoBackup = it },
                icon = Icons.Default.Download
            )

            // Export & Build Section
            SettingsSectionHeader("Export & Build")

            Button(
                onClick = { /* Export project */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Export",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text("Export Project")
            }

            Button(
                onClick = { /* Build APK */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Build",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text("Build APK")
            }

            // Danger Zone Section
            SettingsSectionHeader("Danger Zone", isRed = true)

            OutlinedButton(
                onClick = { showDeleteDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text("Delete Project")
            }

            // Spacing at bottom
            Surface(modifier = Modifier.padding(vertical = 16.dp)) {}
        }
    }

    if (showDeleteDialog) {
        DeleteProjectDialog(
            projectName = appName,
            onConfirm = {
                onDeleteProject()
                showDeleteDialog = false
            },
            onDismiss = { showDeleteDialog = false }
        )
    }
}

@Composable
fun SettingsSectionHeader(
    title: String,
    isRed: Boolean = false
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = if (isRed) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun SettingToggleItem(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: androidx.compose.material.icons.Icons.Filled? = null
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
fun DeleteProjectDialog(
    projectName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Project?") },
        text = {
            Text(
                "Are you sure you want to delete '$projectName'? This action cannot be undone."
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

package com.urbanman700.appbuilder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewScreen(
    projectName: String,
    onBack: () -> Unit
) {
    var buildProgress by remember { mutableFloatStateOf(0f) }
    var isBuildComplete by remember { mutableStateOf(false) }
    var previewText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        // Simulate build process
        for (i in 0..100) {
            buildProgress = i / 100f
            delay(30)
        }
        isBuildComplete = true
        delay(500)
        previewText = "App Preview Loaded Successfully!"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Live Preview - $projectName") },
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
            // Build Progress
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = if (isBuildComplete) "Build Completed" else "Building Project...",
                    style = MaterialTheme.typography.titleMedium
                )
                LinearProgressIndicator(
                    progress = { buildProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )
                Text(
                    text = "${(buildProgress * 100).toInt()}% Complete",
                    style = MaterialTheme.typography.labelMedium
                )
            }

            // Build Steps
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                BuildStepItem(
                    title = "Validating Project Structure",
                    isComplete = buildProgress > 0.2f
                )
                BuildStepItem(
                    title = "Compiling Kotlin Code",
                    isComplete = buildProgress > 0.4f
                )
                BuildStepItem(
                    title = "Processing Resources",
                    isComplete = buildProgress > 0.6f
                )
                BuildStepItem(
                    title = "Linking Libraries",
                    isComplete = buildProgress > 0.8f
                )
                BuildStepItem(
                    title = "Building APK",
                    isComplete = buildProgress > 0.95f
                )
            }

            // Preview Area
            if (isBuildComplete) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Success",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = previewText,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Your app is ready to test!",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }

            // Action Button
            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Back to Editor")
            }
        }
    }
}

@Composable
fun BuildStepItem(
    title: String,
    isComplete: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isComplete) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Complete",
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(8.dp)
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isComplete) Color.Gray else MaterialTheme.colorScheme.onSurface
        )
    }
}

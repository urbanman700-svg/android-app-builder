package com.urbanman700.appbuilder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeEditorScreen(
    projectName: String,
    onBack: () -> Unit,
    onExportAPK: () -> Unit,
    onPreview: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var codeContent by remember { mutableStateOf(getDefaultMainActivityCode()) }

    val tabs = listOf(
        "MainActivity.kt",
        "AndroidManifest.xml",
        "build.gradle.kts",
        "Strings.xml"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Code Editor - $projectName") },
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
            // File Tabs
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title, style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }

            // Code Editor
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color(0xFF1E1E1E))
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Line numbers
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Line numbers column
                    Column(
                        modifier = Modifier
                            .background(color = Color(0xFF252526))
                            .padding(end = 8.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        repeat(codeContent.lines().size) { lineNum ->
                            Text(
                                text = (lineNum + 1).toString().padStart(3),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF858585),
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp
                            )
                        }
                    }

                    // Code content
                    BasicTextField(
                        value = codeContent,
                        onValueChange = { codeContent = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textStyle = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            color = Color(0xFFD4D4D4)
                        )
                    )
                }
            }

            // Bottom Action Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF3C3C3C))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onPreview,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Preview",
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text("Preview")
                }
                Button(
                    onClick = onExportAPK,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Export",
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text("Export APK")
                }
            }
        }
    }
}

private fun getDefaultMainActivityCode(): String {
    return """package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Text("Hello, Android App Builder!")
            }
        }
    }
}
"""
}

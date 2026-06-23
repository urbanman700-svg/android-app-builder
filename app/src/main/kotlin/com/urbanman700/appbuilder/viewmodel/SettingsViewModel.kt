package com.urbanman700.appbuilder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urbanman700.appbuilder.data.models.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val _projectSettings = MutableStateFlow<Map<String, Any>>(emptyMap())
    val projectSettings: StateFlow<Map<String, Any>> = _projectSettings.asStateFlow()

    private val _buildSettings = MutableStateFlow<BuildSettings>(BuildSettings())
    val buildSettings: StateFlow<BuildSettings> = _buildSettings.asStateFlow()

    private val _exportInProgress = MutableStateFlow(false)
    val exportInProgress: StateFlow<Boolean> = _exportInProgress.asStateFlow()

    fun updateProjectSettings(settings: Map<String, Any>) {
        _projectSettings.value = settings
    }

    fun updateBuildSettings(settings: BuildSettings) {
        _buildSettings.value = settings
    }

    fun exportProject(project: Project) {
        viewModelScope.launch {
            _exportInProgress.value = true
            // Simulate export process
            kotlinx.coroutines.delay(2000)
            _exportInProgress.value = false
        }
    }
}

data class BuildSettings(
    val minSdkVersion: Int = 21,
    val targetSdkVersion: Int = 34,
    val enableProguard: Boolean = true,
    val enableMultiDex: Boolean = false,
    val enableNativeDebug: Boolean = false,
    val analyticsEnabled: Boolean = true,
    val crashReportingEnabled: Boolean = true
)

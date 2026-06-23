package com.urbanman700.appbuilder.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PreviewViewModel : ViewModel() {
    private val _buildProgress = MutableStateFlow(0f)
    val buildProgress: StateFlow<Float> = _buildProgress.asStateFlow()

    private val _isBuildComplete = MutableStateFlow(false)
    val isBuildComplete: StateFlow<Boolean> = _isBuildComplete.asStateFlow()

    private val _buildLogs = MutableStateFlow<List<String>>(emptyList())
    val buildLogs: StateFlow<List<String>> = _buildLogs.asStateFlow()

    fun updateProgress(progress: Float) {
        _buildProgress.value = progress
        if (progress >= 1f) {
            _isBuildComplete.value = true
        }
    }

    fun addBuildLog(message: String) {
        _buildLogs.value = _buildLogs.value + message
    }

    fun resetBuild() {
        _buildProgress.value = 0f
        _isBuildComplete.value = false
        _buildLogs.value = emptyList()
    }
}

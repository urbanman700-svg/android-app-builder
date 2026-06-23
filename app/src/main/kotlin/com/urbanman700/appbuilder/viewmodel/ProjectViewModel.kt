package com.urbanman700.appbuilder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urbanman700.appbuilder.data.models.Project
import com.urbanman700.appbuilder.data.repository.IProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(private val projectRepository: IProjectRepository) : ViewModel() {
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    private val _selectedProject = MutableStateFlow<Project?>(null)
    val selectedProject: StateFlow<Project?> = _selectedProject.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadProjects()
    }

    private fun loadProjects() {
        viewModelScope.launch {
            _isLoading.value = true
            projectRepository.getAllProjects().collect { projects ->
                _projects.value = projects
                _isLoading.value = false
            }
        }
    }

    fun createProject(project: Project) {
        viewModelScope.launch {
            projectRepository.createProject(project)
            loadProjects()
        }
    }

    fun updateProject(project: Project) {
        viewModelScope.launch {
            projectRepository.updateProject(project)
            loadProjects()
        }
    }

    fun deleteProject(projectId: Int) {
        viewModelScope.launch {
            projectRepository.deleteProject(projectId)
            loadProjects()
        }
    }

    fun selectProject(project: Project) {
        _selectedProject.value = project
    }
}

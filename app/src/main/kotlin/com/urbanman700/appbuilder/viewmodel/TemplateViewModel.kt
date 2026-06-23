package com.urbanman700.appbuilder.viewmodel

import androidx.lifecycle.ViewModel
import com.urbanman700.appbuilder.data.models.Template
import com.urbanman700.appbuilder.data.repository.TemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TemplateViewModel : ViewModel() {
    private val templateRepository = TemplateRepository()

    private val _templates = MutableStateFlow<List<Template>>(emptyList())
    val templates: StateFlow<List<Template>> = _templates.asStateFlow()

    private val _selectedTemplate = MutableStateFlow<Template?>(null)
    val selectedTemplate: StateFlow<Template?> = _selectedTemplate.asStateFlow()

    init {
        loadTemplates()
    }

    private fun loadTemplates() {
        _templates.value = templateRepository.getAvailableTemplates()
    }

    fun selectTemplate(template: Template) {
        _selectedTemplate.value = template
    }

    fun getTemplateById(templateId: String): Template? {
        return templateRepository.getTemplateById(templateId)
    }
}

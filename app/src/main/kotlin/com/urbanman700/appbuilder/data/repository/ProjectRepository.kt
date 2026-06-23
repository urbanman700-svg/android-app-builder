package com.urbanman700.appbuilder.data.repository

import com.urbanman700.appbuilder.data.local.ProjectDao
import com.urbanman700.appbuilder.data.models.Project
import kotlinx.coroutines.flow.Flow

interface IProjectRepository {
    suspend fun createProject(project: Project): Long
    suspend fun getProject(id: Int): Project?
    fun getAllProjects(): Flow<List<Project>>
    suspend fun updateProject(project: Project)
    suspend fun deleteProject(id: Int)
    fun searchProjects(query: String): Flow<List<Project>>
}

class ProjectRepository(private val projectDao: ProjectDao) : IProjectRepository {
    override suspend fun createProject(project: Project): Long {
        return projectDao.insertProject(project)
    }

    override suspend fun getProject(id: Int): Project? {
        return projectDao.getProjectById(id)
    }

    override fun getAllProjects(): Flow<List<Project>> {
        return projectDao.getAllProjects()
    }

    override suspend fun updateProject(project: Project) {
        projectDao.updateProject(project)
    }

    override suspend fun deleteProject(id: Int) {
        projectDao.deleteProjectById(id)
    }

    override fun searchProjects(query: String): Flow<List<Project>> {
        return projectDao.searchProjects(query)
    }
}

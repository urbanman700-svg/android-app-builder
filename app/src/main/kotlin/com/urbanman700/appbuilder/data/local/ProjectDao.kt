package com.urbanman700.appbuilder.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.urbanman700.appbuilder.data.models.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert
    suspend fun insertProject(project: Project): Long

    @Update
    suspend fun updateProject(project: Project)

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("SELECT * FROM projects WHERE id = :projectId")
    suspend fun getProjectById(projectId: Int): Project?

    @Query("SELECT * FROM projects ORDER BY modifiedAt DESC")
    fun getAllProjects(): Flow<List<Project>>

    @Query("DELETE FROM projects WHERE id = :projectId")
    suspend fun deleteProjectById(projectId: Int)

    @Query("SELECT * FROM projects WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchProjects(searchQuery: String): Flow<List<Project>>
}

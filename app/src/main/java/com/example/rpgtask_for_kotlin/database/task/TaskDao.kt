package com.example.rpgtask_for_kotlin.database.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<Task>>;

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("DELETE FROM task")
    fun allDelete()
}
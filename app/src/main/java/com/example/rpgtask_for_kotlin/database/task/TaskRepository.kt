package com.example.rpgtask_for_kotlin.database.task

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val alltask: Flow<List<Task>> = taskDao.getAllTask()

    @WorkerThread
    suspend fun insert(task: Task){
        taskDao.insert(task)
    }

    @WorkerThread
    suspend fun delete(task: Task){
        taskDao.delete(task)
    }
    @WorkerThread
    suspend fun allDelete(){
        taskDao.allDelete()
    }

}
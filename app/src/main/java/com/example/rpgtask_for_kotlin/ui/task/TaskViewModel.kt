package com.example.rpgtask_for_kotlin.ui.task

import androidx.lifecycle.*
import com.example.rpgtask_for_kotlin.database.task.Task
import com.example.rpgtask_for_kotlin.database.task.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val allTask: LiveData<List<Task>> = repository.alltask.asLiveData()

    suspend fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }
    fun allDelete() = viewModelScope.launch(Dispatchers.IO){
        repository.allDelete()
    }
}

class TaskListViewModelFactory(private val repository: TaskRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
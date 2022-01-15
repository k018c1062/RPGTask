package com.example.rpgtask_for_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.rpgtask_for_kotlin.database.AppDatabase
import com.example.rpgtask_for_kotlin.database.task.TaskRepository
import com.example.rpgtask_for_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(applicationContext,applicationScope) }
    val repository by lazy { TaskRepository(database.taskDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val controller: NavController = Navigation.findNavController(this,R.id.host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigationView,controller)
    }

}
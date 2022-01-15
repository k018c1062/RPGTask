package com.example.rpgtask_for_kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rpgtask_for_kotlin.database.task.Task
import com.example.rpgtask_for_kotlin.database.task.TaskDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "RPG_Task_for_Kotlin",
                )
                    .addCallback(NewsDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .enableMultiInstanceInvalidation()
                    .build()
                INSTANCE = instance
                return instance
            }
        }


//        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "RPG_Task_Database"
//                ).enableMultiInstanceInvalidation().fallbackToDestructiveMigration().build()
//                INSTANCE = instance
//                return instance
//            }
//
//        }
    }
    class NewsDatabaseCallback (private val scope: CoroutineScope):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{ database ->
                scope.launch {
                    populateDatabase(database.taskDao())
                }
            }
        }
        private suspend fun populateDatabase(newsDao: TaskDao){

        }
    }
}
package com.example.rpgtask_for_kotlin.database.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task (
    @PrimaryKey @ColumnInfo(name = "task_id") val taskId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "memo") val memo: String,
    @ColumnInfo(name = "time_limit") val timeLimit: String,
    @ColumnInfo(name = "repeat_setting") val repeatSetting: String,
    @ColumnInfo(name = "repeat_count") val repeatCount: String,
    @ColumnInfo(name = "growth_item") val growthItem: String,
    @ColumnInfo(name = "difficulty") val difficulty: String,
    @ColumnInfo(name = "importance") val importance: String,
    @ColumnInfo(name = "last_completed") val lastCompleted: String,
    @ColumnInfo(name = "task_tag") val taskTag: String,
    @Ignore var expandable: Boolean){
    // _______________
    //　　＜●√
    //　　　 |
    //　　　くく
    //分かりやすいように空間を開けてみる
    constructor(taskId: String,name: String,  memo: String, timeLimit: String,repeatSetting: String,repeatCount: String, growthItem: String, difficulty: String, importance: String,  lastCompleted: String,  taskTag: String):
            this(taskId,name,memo,timeLimit,repeatSetting,repeatCount,growthItem,difficulty,importance,lastCompleted,taskTag,false)

}


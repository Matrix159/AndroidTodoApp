package com.jeldridge.todoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeldridge.todoapp.data.local.database.dao.TodoDao
import com.jeldridge.todoapp.data.local.database.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

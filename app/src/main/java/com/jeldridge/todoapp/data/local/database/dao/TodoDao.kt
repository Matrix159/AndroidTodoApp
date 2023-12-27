package com.jeldridge.todoapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jeldridge.todoapp.data.local.database.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
  @Query("SELECT * FROM todo ORDER BY id ASC")
  fun getTodos(): Flow<List<TodoEntity>>

  @Insert
  suspend fun insertTodo(todo: TodoEntity)

  @Delete
  suspend fun deleteTodo(todo: TodoEntity)
}
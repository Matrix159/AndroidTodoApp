package com.jeldridge.todoapp.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class Todo(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo ORDER BY uid DESC LIMIT 10")
    fun getTodos(): Flow<List<Todo>>

    @Insert
    suspend fun insertTodo(item: Todo)
}

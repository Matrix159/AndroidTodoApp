package com.jeldridge.todoapp.data.local.database.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import com.jeldridge.todoapp.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
) {
    fun toTodo(): Todo {
        return Todo(
            id = id,
            name = name
        )
    }

    companion object {
        fun fromTodo(todo: Todo): TodoEntity {
            return TodoEntity(
                id = todo.id,
                name = todo.name
            )
        }
    }
}

package com.jeldridge.todoapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeldridge.todoapp.data.model.Todo

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

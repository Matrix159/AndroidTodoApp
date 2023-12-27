package com.jeldridge.todoapp.data

import com.jeldridge.todoapp.data.local.database.dao.TodoDao
import com.jeldridge.todoapp.data.local.database.entity.TodoEntity
import com.jeldridge.todoapp.data.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface TodoRepository {
  val todos: Flow<List<Todo>>

  suspend fun add(name: String)
  suspend fun delete(todo: Todo)
}

class DefaultTodoRepository @Inject constructor(
  private val todoDao: TodoDao
) : TodoRepository {

  override val todos: Flow<List<Todo>> =
    todoDao.getTodos().map { items -> items.map { it.toTodo() } }

  override suspend fun add(name: String) {
    todoDao.insertTodo(TodoEntity(name = name))
  }

  override suspend fun delete(todo: Todo) {
    todoDao.deleteTodo(TodoEntity.fromTodo(todo))
  }
}

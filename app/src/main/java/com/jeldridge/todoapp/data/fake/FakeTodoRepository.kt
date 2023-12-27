package com.jeldridge.todoapp.data.fake

import com.jeldridge.todoapp.data.TodoRepository
import com.jeldridge.todoapp.data.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeTodoRepository : TodoRepository {

  private val data = MutableStateFlow<List<Todo>>(emptyList())

  override val todos: Flow<List<Todo>>
    get() = data.asStateFlow()

  override suspend fun add(name: String) {
    val currentList = data.value
    data.emit(currentList + Todo(name = name))
  }

  override suspend fun delete(todo: Todo) {
    val currentList = data.value
    val newList = currentList.filterNot { item -> item.id == todo.id }
    data.emit(newList)
  }
}
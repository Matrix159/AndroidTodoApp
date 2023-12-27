package com.jeldridge.todoapp.data.fake

import com.jeldridge.todoapp.data.TodoRepository
import com.jeldridge.todoapp.data.model.Todo
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow

class FakeTodoRepository : TodoRepository {

  private val data =
    MutableSharedFlow<List<Todo>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
  private val currentData: List<Todo> get() = data.replayCache.firstOrNull() ?: emptyList()

  var todoFlowShouldError = false
  override val todos: Flow<List<Todo>>
    get() = if (todoFlowShouldError) flow {
      throw Exception("Test exception")
    } else data.asSharedFlow()

  override suspend fun add(name: String) {
    data.emit(currentData + Todo(name = name))
  }

  override suspend fun delete(todo: Todo) {
    val newList = currentData.filterNot { item -> item.id == todo.id }
    data.emit(newList)
  }
}
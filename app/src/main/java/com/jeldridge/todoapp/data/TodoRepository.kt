package com.jeldridge.todoapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.jeldridge.todoapp.data.local.database.Todo
import com.jeldridge.todoapp.data.local.database.TodoDao
import javax.inject.Inject

interface TodoRepository {
    val todos: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultTodoRepository @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {

    override val todos: Flow<List<String>> =
        todoDao.getTodos().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        todoDao.insertTodo(Todo(name = name))
    }
}

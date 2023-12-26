package com.jeldridge.todoapp.data.local.fake

import com.jeldridge.todoapp.data.local.database.dao.TodoDao
import com.jeldridge.todoapp.data.local.database.entity.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTodoDao : TodoDao {

    private val data = mutableListOf<TodoEntity>()

    override fun getTodos(): Flow<List<TodoEntity>> = flow {
        emit(data)
    }

    override suspend fun insertTodo(todo: TodoEntity) {
        data.add(todo)
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        data.remove(todo)
    }
}

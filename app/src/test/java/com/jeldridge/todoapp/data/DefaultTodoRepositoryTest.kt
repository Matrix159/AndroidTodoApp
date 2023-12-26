package com.jeldridge.todoapp.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.jeldridge.todoapp.data.local.database.Todo
import com.jeldridge.todoapp.data.local.database.TodoDao

/**
 * Unit tests for [DefaultTodoRepository].
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class DefaultTodoRepositoryTest {

    @Test
    fun todos_newItemSaved_itemIsReturned() = runTest {
        val repository = DefaultTodoRepository(FakeTodoDao())

        repository.add("Repository")

        assertEquals(repository.todos.first().size, 1)
    }

}

private class FakeTodoDao : TodoDao {

    private val data = mutableListOf<Todo>()

    override fun getTodos(): Flow<List<Todo>> = flow {
        emit(data)
    }

    override suspend fun insertTodo(item: Todo) {
        data.add(0, item)
    }
}

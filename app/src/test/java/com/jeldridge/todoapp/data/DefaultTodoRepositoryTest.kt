package com.jeldridge.todoapp.data

import com.jeldridge.todoapp.data.local.fake.FakeTodoDao
import com.jeldridge.todoapp.data.model.Todo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for [DefaultTodoRepository].
 */
class DefaultTodoRepositoryTest {

  @Test
  fun todos_newItemSaved_itemIsReturned() = runTest {
    val repository = DefaultTodoRepository(FakeTodoDao())

    val expectedTodoName = "Repository"
    repository.add(expectedTodoName)

    val todos = repository.todos.first()
    assertEquals(todos.size, 1)
    assertEquals(Todo(name = expectedTodoName), todos.first())
  }

  @Test
  fun todos_deleteTodo_isRemoved() = runTest {
    val repository = DefaultTodoRepository(FakeTodoDao())

    val expectedTodoName = "Repository"
    repository.add(expectedTodoName)

    repository.delete(Todo(name = expectedTodoName))
    assertEquals(0, repository.todos.first().size)
  }

}

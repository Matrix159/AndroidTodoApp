package com.jeldridge.todoapp.data

import com.jeldridge.todoapp.data.local.fake.FakeTodoDao
import com.jeldridge.todoapp.data.model.Todo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [DefaultTodoRepository].
 */
class DefaultTodoRepositoryTest {

  private lateinit var repository: TodoRepository

  @Before
  fun setup() {
    repository = DefaultTodoRepository(FakeTodoDao())
  }

  @Test
  fun `todos are empty by default`() = runTest {
    val todos = repository.todos.first()
    assertEquals(0, todos.size)
  }

  @Test
  fun `saves a todo when adding by name`() = runTest {
    val expectedTodoName = "Repository"
    repository.add(expectedTodoName)

    val todos = repository.todos.first()
    assertEquals(1, todos.size)
    assertEquals(Todo(name = expectedTodoName), todos.first())
  }

  @Test
  fun `deleting a todo removes it from todos`() = runTest {
    val expectedTodoName = "Repository"
    repository.add(expectedTodoName)

    repository.delete(Todo(name = expectedTodoName))
    assertEquals(0, repository.todos.first().size)
  }
}

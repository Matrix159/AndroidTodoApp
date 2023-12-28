package com.jeldridge.todoapp.ui.todo


import com.jeldridge.todoapp.MainDispatcherRule
import com.jeldridge.todoapp.data.fake.FakeTodoRepository
import com.jeldridge.todoapp.data.model.Todo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var todoRepository: FakeTodoRepository
  private lateinit var viewModel: TodoViewModel

  @Before
  fun setup() {
    todoRepository = FakeTodoRepository()
    viewModel = TodoViewModel(todoRepository)
  }

  @Test
  fun `UI state is initially Loading`() = runTest {
    assertEquals(TodoUiState.Loading, viewModel.uiState.first())
  }

  @Test
  fun `UI state is Success after loading data`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
    val expectedTodoName = "Test todo"
    todoRepository.add(expectedTodoName)

    val state = viewModel.uiState.value
    assertIs<TodoUiState.Success>(state)
    assertEquals(1, state.data.size)

    collectJob.cancel()
  }

  @Test
  fun `UI state is error after catching exception`() = runTest {
    todoRepository = FakeTodoRepository()
    todoRepository.todoFlowShouldError = true
    viewModel = TodoViewModel(todoRepository)

    val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

    val state = viewModel.uiState.value
    assertIs<TodoUiState.Error>(state)

    collectJob.cancel()
  }

  @Test
  fun `addTodo adds todo to UI state`() = runTest {
    val firstTodo = "Test todo"
    todoRepository.add(firstTodo)
    val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }


    val expectedTodo = "new todo"
    viewModel.addTodo("new todo")

    assertEquals(
      TodoUiState.Success(
        data = listOf(
          Todo(id = 1, name = firstTodo),
          Todo(id = 2, name = expectedTodo)
        )
      ), viewModel.uiState.value
    )
    collectJob.cancel()
  }

  @Test
  fun `deleteTodo removes existing todo from UI state`() = runTest {
    val firstTodo = "Test todo"
    todoRepository.add(firstTodo)
    val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

    val stateBeforeDelete = viewModel.uiState.value
    assertIs<TodoUiState.Success>(stateBeforeDelete)

    viewModel.deleteTodo(stateBeforeDelete.data.first())

    val stateAfterDelete = viewModel.uiState.value
    assertIs<TodoUiState.Success>(stateAfterDelete)
    assertEquals(0, stateAfterDelete.data.size)

    collectJob.cancel()
  }
}



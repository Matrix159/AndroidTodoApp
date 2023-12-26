package com.jeldridge.todoapp.ui.todo


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.jeldridge.todoapp.data.TodoRepository

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class TodoViewModelTest {
    @Test
    fun uiState_initiallyLoading() = runTest {
        val viewModel = TodoViewModel(FakeTodoRepository())
        assertEquals(viewModel.uiState.first(), TodoUiState.Loading)
    }

    @Test
    fun uiState_onItemSaved_isDisplayed() = runTest {
        val viewModel = TodoViewModel(FakeTodoRepository())
        assertEquals(viewModel.uiState.first(), TodoUiState.Loading)
    }
}

private class FakeTodoRepository : TodoRepository {

    private val data = mutableListOf<String>()

    override val todos: Flow<List<String>>
        get() = flow { emit(data.toList()) }

    override suspend fun add(name: String) {
        data.add(0, name)
    }
}
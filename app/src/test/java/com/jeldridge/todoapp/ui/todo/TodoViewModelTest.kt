package com.jeldridge.todoapp.ui.todo


import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.jeldridge.todoapp.data.fake.FakeTodoRepository

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



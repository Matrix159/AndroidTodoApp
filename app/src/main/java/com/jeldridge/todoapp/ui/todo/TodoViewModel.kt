package com.jeldridge.todoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.jeldridge.todoapp.data.TodoRepository
import com.jeldridge.todoapp.data.model.Todo
import com.jeldridge.todoapp.ui.todo.TodoUiState.Error
import com.jeldridge.todoapp.ui.todo.TodoUiState.Loading
import com.jeldridge.todoapp.ui.todo.TodoUiState.Success
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val uiState: StateFlow<TodoUiState> = todoRepository
        .todos.map<List<Todo>, TodoUiState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            Loading
        )

    fun addTodo(name: String) {
        viewModelScope.launch {
            todoRepository.add(name)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.delete(todo)
        }
    }
}

sealed interface TodoUiState {
    data object Loading : TodoUiState
    data class Error(val throwable: Throwable) : TodoUiState
    data class Success(val data: List<Todo>) : TodoUiState
}

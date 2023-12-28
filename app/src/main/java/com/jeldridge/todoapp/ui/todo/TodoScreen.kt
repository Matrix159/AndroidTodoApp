package com.jeldridge.todoapp.ui.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeldridge.todoapp.data.model.Todo
import com.jeldridge.todoapp.ui.TodoAppPreview

@Composable
fun TodoScreen(modifier: Modifier = Modifier, viewModel: TodoViewModel = hiltViewModel()) {
  val state by viewModel.uiState.collectAsStateWithLifecycle()
  when (val uiState = state) {
    is TodoUiState.Error -> Text("Error occurred ${uiState.throwable}")
    TodoUiState.Loading -> Text("Loading")
    is TodoUiState.Success -> TodoScreen(
      todos = uiState.data,
      onSave = viewModel::addTodo,
      onDelete = viewModel::deleteTodo,
      modifier = modifier
    )
  }
}

@Composable
internal fun TodoScreen(
  todos: List<Todo>,
  onSave: (name: String) -> Unit,
  onDelete: (todo: Todo) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier.padding(16.dp)) {
    var todoToDelete by rememberSaveable { mutableStateOf<Todo?>(null) }
    todoToDelete?.let {
      DeleteTodoDialog(
        todo = it,
        onDelete = {
          onDelete(it)
          todoToDelete = null
        },
        onDismissRequest = {
          todoToDelete = null
        })
    }
    TodoList(
      todos = todos,
      onDelete = { todo -> todoToDelete = todo },
      modifier = Modifier.weight(1f)
    )
    Spacer(Modifier.height(16.dp))
    TodoInput(onAdd = { name ->
      onSave(name)
    })
  }
}

@TodoAppPreview
@Composable
private fun TodoScreenPreview() {
  TodoAppPreview {
    TodoScreen(
      listOf(
        Todo(id = 0, name = "Take out the trash"),
        Todo(id = 1, name = "Clean stuff"),
        Todo(id = 2, name = "Sustenance")
      ),
      onSave = {},
      onDelete = {}
    )
  }
}


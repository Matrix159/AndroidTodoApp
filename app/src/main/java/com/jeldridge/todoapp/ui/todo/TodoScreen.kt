package com.jeldridge.todoapp.ui.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeldridge.todoapp.data.model.Todo
import com.jeldridge.todoapp.ui.TodoAppPreview
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

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
    var nameTodo by remember { mutableStateOf("Compose") }
    Column(modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = nameTodo,
                onValueChange = { nameTodo = it }
            )

            Button(modifier = Modifier.width(96.dp), onClick = { onSave(nameTodo) }) {
                Text("Save")
            }
        }
        LazyColumn {
            items(todos, key = { it.id }) { todo ->
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Saved item: ${todo.name}")
                        IconButton(onClick = { onDelete(todo) }) {
                            Icon(
                                Icons.Default.Delete,
                                "Delete todo",
                                tint = Color.Red
                            )
                        }
                    }
                    Divider()
                }
            }
            item {
                // TODO: Dynamic add item here
            }
        }
    }
}

@TodoAppPreview
@Composable
private fun DefaultPreview() {
    TodoAppPreview {
        TodoScreen(
            listOf(
                Todo(id = 0, name = "Compose"),
                Todo(id = 0, name = "Room"),
                Todo(id = 0, name = "Kotlin")
            ),
            onSave = {},
            onDelete = {}
        )
    }
}


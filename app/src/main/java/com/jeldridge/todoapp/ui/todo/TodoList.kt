package com.jeldridge.todoapp.ui.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jeldridge.todoapp.R
import com.jeldridge.todoapp.data.model.Todo
import com.jeldridge.todoapp.ui.TodoAppPreview

@Composable
fun TodoList(
  todos: List<Todo>,
  onDelete: (todo: Todo) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
  ) {
    items(todos, key = { it.id }) { todo ->
      Column(
        modifier = Modifier.padding(4.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(todo.name, modifier = Modifier.weight(1f))
          IconButton(onClick = { onDelete(todo) }) {
            Icon(
              Icons.Default.Delete,
              stringResource(R.string.delete_todo, todo.name),
              tint = Color.Red
            )
          }
        }
        Divider()
      }
    }
  }
}

@TodoAppPreview
@Composable
fun TodoListPreview() {
  TodoAppPreview {
    TodoList(
      todos = listOf(
        Todo(id = 1, name = "Take the trash out"),
        Todo(id = 2, name = "Do chores"),
        Todo(id = 3, name = "Party")
      ),
      onDelete = {},
    )
  }
}
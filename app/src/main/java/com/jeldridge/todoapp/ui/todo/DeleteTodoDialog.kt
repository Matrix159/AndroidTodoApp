package com.jeldridge.todoapp.ui.todo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jeldridge.todoapp.R
import com.jeldridge.todoapp.data.model.Todo
import com.jeldridge.todoapp.ui.TodoAppPreview

@Composable
fun DeleteTodoDialog(
  todo: Todo,
  onDelete: () -> Unit,
  onDismissRequest: () -> Unit,
  modifier: Modifier = Modifier
) {
  AlertDialog(
    icon = {
      Icon(Icons.Default.Delete, contentDescription = null)
    },
    title = {
      Text(text = stringResource(R.string.delete_todo_dialog_title))
    },
    text = {
      Text(text = stringResource(R.string.delete_todo_confirmation, todo.name))
    },
    onDismissRequest = {
      onDismissRequest()
    },
    confirmButton = {
      TextButton(
        onClick = {
          onDelete()
        }
      ) {
        Text(stringResource(R.string.delete))
      }
    },
    dismissButton = {
      TextButton(
        onClick = {
          onDismissRequest()
        }
      ) {
        Text(stringResource(R.string.cancel))
      }
    },
    modifier = modifier
  )
}

@TodoAppPreview
@Composable
fun DeleteTodoDialogPreview() {
  TodoAppPreview {
    DeleteTodoDialog(
      todo = Todo(id = 8586, name = "Take the trash out"),
      onDelete = {},
      onDismissRequest = {},
    )
  }
}
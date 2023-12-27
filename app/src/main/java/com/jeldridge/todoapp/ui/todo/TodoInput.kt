package com.jeldridge.todoapp.ui.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jeldridge.todoapp.R
import com.jeldridge.todoapp.ui.TodoAppPreview

@Composable
fun TodoInput(
  onAdd: (todoName: String) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    modifier = modifier
  ) {
    var todoName by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
      value = todoName,
      onValueChange = { todoName = it },
      modifier = Modifier.weight(1f)
    )

    Button(
      enabled = todoName.isNotEmpty(),
      onClick = {
        onAdd(todoName)
        todoName = ""
      }
    ) {
      Text(
        softWrap = false,
        text = stringResource(R.string.add)
      )
    }
  }
}

@TodoAppPreview
@Composable
fun TodoInputPreview() {
  TodoAppPreview {
    TodoInput(
      onAdd = {},
    )
  }
}
package com.jeldridge.todoapp.ui

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jeldridge.todoapp.ui.theme.TodoTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class TodoAppPreview

@Composable
fun TodoAppPreview(content: @Composable () -> Unit = {}) {
  TodoTheme {
    Surface {
      content()
    }
  }
}
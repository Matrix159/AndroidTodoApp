package com.jeldridge.todoapp.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jeldridge.todoapp.R
import com.jeldridge.todoapp.ui.theme.TodoTheme

@OptIn(
  ExperimentalLayoutApi::class,
  ExperimentalMaterial3Api::class
)
@Composable
fun TodoApp() {
  TodoTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.background
    ) {
      Scaffold(topBar = {
        CenterAlignedTopAppBar(
          colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
          ),
          title = {
            Text(stringResource(R.string.app_bar_todo_list))
          }
        )
      }) { padding ->
        TodoAppNav(
          modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .consumeWindowInsets(padding)
        )
      }
    }
  }
}
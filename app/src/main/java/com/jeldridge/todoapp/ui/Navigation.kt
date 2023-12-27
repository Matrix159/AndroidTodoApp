package com.jeldridge.todoapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeldridge.todoapp.ui.todo.TodoScreen

@Composable
fun TodoAppNav(modifier: Modifier = Modifier) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = "todoScreen",
    modifier = modifier
  ) {
    composable("todoScreen") { TodoScreen() }
  }
}

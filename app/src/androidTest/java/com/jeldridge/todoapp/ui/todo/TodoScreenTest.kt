package com.jeldridge.todoapp.ui.todo

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jeldridge.todoapp.data.model.Todo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI tests for [TodoScreen].
 */
@RunWith(AndroidJUnit4::class)
class TodoScreenTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Before
  fun setup() {
    composeTestRule.setContent {
      TodoScreen(FAKE_DATA, onSave = {}, onDelete = {})
    }
  }

  @Test
  fun firstItem_exists() {
    composeTestRule.onNodeWithText(FAKE_DATA.first().name).assertExists()
  }
}

private val FAKE_DATA =
  listOf(Todo(id = 0, name = "Compose"), Todo(id = 1, name = "Room"), Todo(id = 2, name = "Kotlin"))

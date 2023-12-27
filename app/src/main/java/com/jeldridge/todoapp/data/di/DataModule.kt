package com.jeldridge.todoapp.data.di

import com.jeldridge.todoapp.data.DefaultTodoRepository
import com.jeldridge.todoapp.data.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

  @Singleton
  @Binds
  fun bindsTodoRepository(
    todoRepository: DefaultTodoRepository
  ): TodoRepository
}

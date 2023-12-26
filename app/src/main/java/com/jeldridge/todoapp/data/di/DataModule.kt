package com.jeldridge.todoapp.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.jeldridge.todoapp.data.TodoRepository
import com.jeldridge.todoapp.data.DefaultTodoRepository
import javax.inject.Inject
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

class FakeTodoRepository @Inject constructor() : TodoRepository {
    override val todos: Flow<List<String>> = flowOf(fakeTodos)

    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeTodos = listOf("One", "Two", "Three")

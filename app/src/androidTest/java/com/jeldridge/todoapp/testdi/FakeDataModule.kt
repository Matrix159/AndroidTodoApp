package com.jeldridge.todoapp.testdi

import com.jeldridge.todoapp.data.TodoRepository
import com.jeldridge.todoapp.data.di.DataModule
import com.jeldridge.todoapp.data.fake.FakeTodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
  components = [SingletonComponent::class],
  replaces = [DataModule::class]
)
interface FakeDataModule {

  @Binds
  abstract fun bindRepository(
    fakeRepository: FakeTodoRepository
  ): TodoRepository
}

package com.jeldridge.todoapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Todo(
  val id: Int = 0,
  val name: String,
): Parcelable

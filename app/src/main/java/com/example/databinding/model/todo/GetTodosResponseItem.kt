package com.example.databinding.model.todo

data class GetTodosResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)
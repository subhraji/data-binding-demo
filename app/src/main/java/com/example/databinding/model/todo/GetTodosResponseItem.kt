package com.example.databinding.model.todo

data class GetTodosResponseItem(
    var completed: Boolean,
    var id: Int,
    var title: String,
    var userId: Int,
    var image: String?= null
)
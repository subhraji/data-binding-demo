package com.example.databinding.retrofit

import com.example.databinding.model.todo.GetTodosResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET("todos")
    suspend fun getTodos(): GetTodosResponse?
}
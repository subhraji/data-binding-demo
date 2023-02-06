package com.example.databinding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databinding.model.Outcome
import com.example.databinding.model.todo.GetTodosResponse
import com.example.databinding.repository.NewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(private val repository: NewRepository) : ViewModel() {
    private var _response = MutableLiveData<Outcome<GetTodosResponse?>?>()
    val response: MutableLiveData<Outcome<GetTodosResponse?>?> = _response

    fun getTodos() = viewModelScope.launch {
        repository.getTodos().onStart {
            _response.value = Outcome.loading(true)
        }.catch {
            _response.value = Outcome.Failure(it)
        }.collect {
            _response.value = Outcome.success(it)
        }
    }

    fun navigationComplete(){
        _response.value = null
    }
}
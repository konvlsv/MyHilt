package com.example.myhilt.ui

import androidx.lifecycle.ViewModel
import com.example.myhilt.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MyExampleViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    init {
        loadUser()
    }

    private fun loadUser() {
        _userName.value = repository.getUserName()
    }
}
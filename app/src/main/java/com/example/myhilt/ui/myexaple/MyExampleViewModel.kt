package com.example.myhilt.ui.myexaple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhilt.domain.GetUserProfileUseCase
import com.example.myhilt.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyExampleViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            // Благодаря оператору 'invoke' мы вызываем экземпляр класса как обычную функцию
            _userName.value = getUserProfileUseCase(userId = 1)
        }
    }
}
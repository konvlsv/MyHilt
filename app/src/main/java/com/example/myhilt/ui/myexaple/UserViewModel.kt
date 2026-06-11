package com.example.myhilt.ui.myexaple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhilt.domain.GetUserProfileUseCase
import com.example.myhilt.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    // Преобразуем Flow из UseCase в StateFlow для Compose
    // Теперь тип StateFlow — это User?
    val userState: StateFlow<User?> = getUserProfileUseCase.getUserStream(userId = 1)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null // Изначально данных нет
        )

    init {
        // Запускаем фоновое обновление сети. Данные скачаются, упадут в Room,
        // и userName автоматически обновится!
        viewModelScope.launch {
            getUserProfileUseCase.refresh(userId = 1)
        }
    }
}
package com.example.myhilt.ui.myexaple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhilt.domain.GetUserProfileUseCase
import com.example.myhilt.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyExampleViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    // Преобразуем Flow из UseCase в StateFlow для Compose
    val userName: StateFlow<String> = getUserProfileUseCase.getNameStream(userId = 1)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Загрузка..."
        )

    init {
        // Запускаем фоновое обновление сети. Данные скачаются, упадут в Room,
        // и userName автоматически обновится!
        viewModelScope.launch {
            getUserProfileUseCase.refresh(userId = 1)
        }
    }
}
package com.example.myhilt.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhilt.domain.GetUserProfileUseCase
import com.example.myhilt.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    // SavedStateHandle содержит аргументы, переданные через навигацию
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Извлекаем userId, который придет из маршрута навигации (строка "userId")
    // Извлекаем как String и конвертируем в Int
    private val userId: Int = checkNotNull(savedStateHandle.get<String>("userId")).toInt()

    val userDetailState: StateFlow<User?> = getUserProfileUseCase.getUserStream(userId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}
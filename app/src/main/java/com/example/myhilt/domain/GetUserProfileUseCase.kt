package com.example.myhilt.domain

import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    // Хилт сам прокинет сюда репозиторий, так как мы настроили его раньше
    private val repository: UserRepository
) {
    // Оператор invoke позволяет вызывать класс как функцию: getProfileUseCase()
    suspend operator fun invoke(userId: Int): String {
        val rawName = repository.getUserName(userId)

        // Здесь живет бизнес-логика. Например, добавим форматирование:
        return if (rawName.isBlank() || rawName == "Ошибка загрузки") {
            "Гость"
        } else {
            rawName.uppercase() // Пусть на экране имя будет капсом
        }
    }
}
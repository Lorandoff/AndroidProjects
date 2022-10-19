package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.entity.GameInterface
import com.example.myapplication.domain.entity.Levels
import com.example.myapplication.domain.repository.Repository

class GetGameSettingsUseCase(
    private val repository: Repository
) {
    operator fun invoke(levels: Levels) : GameInterface {
        return repository.getGameSettings(levels)
    }

}
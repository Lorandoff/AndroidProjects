package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.entity.Questions
import com.example.myapplication.domain.repository.Repository

class GenerateQuestionsUseCase(
    private val repository: Repository
) {
    operator fun invoke(maxSumValue : Int) : Questions{
        return repository.generateQuestions(maxSumValue, COUNT_OF_OPTIONS)
    }
    companion object {
        private val COUNT_OF_OPTIONS = 6
    }
}
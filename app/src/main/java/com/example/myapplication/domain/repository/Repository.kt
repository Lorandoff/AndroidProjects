package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.GameInterface
import com.example.myapplication.domain.entity.Levels
import com.example.myapplication.domain.entity.Questions

interface Repository {
    fun generateQuestions(
        maxSumValue : Int,
        countofSettings : Int
    ) : Questions
    fun getGameSettings(level : Levels) : GameInterface
}
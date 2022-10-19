package com.example.myapplication.data

import com.example.myapplication.domain.entity.GameInterface
import com.example.myapplication.domain.entity.Levels
import com.example.myapplication.domain.entity.Questions
import com.example.myapplication.domain.repository.Repository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : Repository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1
    override fun generateQuestions(maxSumValue: Int, countofSettings: Int): Questions {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val RightVersion = sum - visibleNumber
        options.add(RightVersion)
        val from = max(RightVersion - countofSettings, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, RightVersion + countofSettings)
        while (options.size < countofSettings){
            options.add(Random.nextInt(from, to))
        }
        return Questions(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Levels): GameInterface {
       return when(level){
           Levels.TEST -> {
               GameInterface(
                   10,
                   3,
                   50,
                   8
               )
           }
           Levels.EASY -> {
               GameInterface(
                   10,
                   10,
                   70,
                   60
               )
           }
           Levels.NORMAL -> {
               GameInterface(
                   20,
                   20,
                   80,
                   40
               )
           }
           Levels.HARD -> {
               GameInterface(
                   30,
                   30,
                   90,
                   40
               )
           }
       }
    }
}
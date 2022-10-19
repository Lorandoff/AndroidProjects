package com.example.myapplication.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.GameRepositoryImpl
import com.example.myapplication.domain.entity.GameInterface
import com.example.myapplication.domain.entity.GameResult
import com.example.myapplication.domain.entity.Levels
import com.example.myapplication.domain.entity.Questions
import com.example.myapplication.domain.usecase.GenerateQuestionsUseCase
import com.example.myapplication.domain.usecase.GetGameSettingsUseCase

class ViewModel(private val application: Application,
                private val levels: Levels) : ViewModel() {
    private lateinit var Gameinterface : GameInterface
    private val _formattedTime = MutableLiveData<String>()
    val formattedTime : LiveData<String>
    get() = _formattedTime
    private val _questions = MutableLiveData<Questions>()
    val questions : LiveData<Questions>
    get() = _questions
    private val _percentofRightAnswers = MutableLiveData<Int>()
    val percentofRightAnswers : LiveData<Int>
    get() = _percentofRightAnswers
    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers : LiveData<String>
    get() = _progressAnswers
    private val _enoughcountofRightAnswers = MutableLiveData<Boolean>()
    val enoughcountofRightAnswers : LiveData<Boolean>
    get() = _enoughcountofRightAnswers
    private val _enoughpercentofRightAnswers = MutableLiveData<Boolean>()
    val enoughpercentofRightAnswers : LiveData<Boolean>
    get() = _enoughpercentofRightAnswers
    private val _minPercent = MutableLiveData<Int>()
    val minPercent : LiveData<Int>
    get() = _minPercent
    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult : LiveData<GameResult>
    get() = _gameResult
    private var countofrightAnswer = 0
    private var countofAnswer = 0
    private val timer : CountDownTimer? = null

    private val repository = GameRepositoryImpl
    private val GenerateQuestions = GenerateQuestionsUseCase(repository)
    private val GetGameSettings = GetGameSettingsUseCase(repository)

    init {
        getGameSettings()
        startTimer()
        generateQuestions()
        updateProgress()
    }
    private fun startGame(){
        getGameSettings()
        startTimer()
        generateQuestions()
        updateProgress()
    }
    private fun getGameSettings() {
        this.Gameinterface = GetGameSettings(levels)
        _minPercent.value = Gameinterface.minPercentofRightAnswer
    }
    private fun generateQuestions() {
        _questions.value = GenerateQuestions(Gameinterface.maxSumValue)
    }
    fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        generateQuestions()
    }
    private fun checkAnswer(number : Int) {
        val rightAnswer = questions.value?.rightAnswer
        if (number == rightAnswer){
            countofrightAnswer++
        }
        countofAnswer++


    }
    private fun updateProgress() {
        val percent = calculatepercentofrightAnswers()
        _percentofRightAnswers.value = percent
        _progressAnswers.value = String.format(
            "Количество правильных ответов %s, минимальное количество правильных ответов %s",
        countofrightAnswer,
        Gameinterface.minCountofRightAnswer)
        _enoughcountofRightAnswers.value = countofrightAnswer >= Gameinterface.minCountofRightAnswer
        _enoughpercentofRightAnswers.value = percent >= Gameinterface.minPercentofRightAnswer

    }
    private fun calculatepercentofrightAnswers() : Int{
        if (countofAnswer == 0) {
            return 0
        }
        return ((countofrightAnswer / countofAnswer.toDouble()) * 100).toInt()
    }
    private fun startTimer() {
        val timer = object : CountDownTimer(
            Gameinterface.gameTimeinSeconds * MILLIS_IN_SECOND,
            MILLIS_IN_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }

        }
        timer?.start()
    }
    private fun formatTime(millisUntilFinished : Long) : String {
        val seconds = millisUntilFinished / MILLIS_IN_SECOND
        val minutes = seconds / SECONDS_IN_MINUTE
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTE)
        return String.format("%02d : %02d", minutes, leftSeconds)
    }
    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughcountofRightAnswers.value == true && enoughpercentofRightAnswers.value == true,
            countofrightAnswer,
            countofAnswer,
            Gameinterface
        )

    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
    companion object {
        private val MILLIS_IN_SECOND = 1000L
        private val SECONDS_IN_MINUTE = 60
    }
}
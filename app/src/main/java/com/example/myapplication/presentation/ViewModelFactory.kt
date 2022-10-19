package com.example.myapplication.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.entity.Levels
import java.lang.RuntimeException

class ViewModelFactory(private val levels: Levels,
                       private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //if (modelClass.isAssignableFrom(ViewModel::class.java)) {
        return ViewModel(application, levels) as T
        //}
       // throw RuntimeException("Ошибочка - не тот класс")
    }
}
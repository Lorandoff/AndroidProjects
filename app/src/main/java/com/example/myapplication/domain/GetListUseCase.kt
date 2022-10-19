package com.example.myapplication.domain

import androidx.lifecycle.LiveData
import com.example.myapplication.Plant

class GetListUseCase(private val plantRepository: PlantRepository) {
    fun GetList() : LiveData<List<Plant>>{
        return plantRepository.GetList()
    }
}
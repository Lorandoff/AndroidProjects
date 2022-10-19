package com.example.myapplication.domain

import com.example.myapplication.Plant

class GetIdUseCase(private val plantRepository: PlantRepository) {
    fun GetIdList(PlantId : Int) : Plant{
        return plantRepository.GetIdList(PlantId)
    }
}
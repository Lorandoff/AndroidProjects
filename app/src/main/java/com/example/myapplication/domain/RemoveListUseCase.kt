package com.example.myapplication.domain

import com.example.myapplication.Plant

class RemoveListUseCase(private val plantRepository: PlantRepository) {
    fun RemoveList(plant: Plant){
        plantRepository.RemoveList(plant)
    }
}
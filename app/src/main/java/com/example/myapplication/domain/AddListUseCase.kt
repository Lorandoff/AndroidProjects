package com.example.myapplication.domain

import com.example.myapplication.Plant

class AddListUseCase(private val plantRepository: PlantRepository) {
    fun AddList(plant: Plant){
        plantRepository.AddList(plant)
    }
}
package com.example.myapplication.domain

import com.example.myapplication.Plant

class EditListUseCase(private val plantRepository: PlantRepository) {
    fun EditList(plant: Plant){
        plantRepository.EditList(plant)
    }
}
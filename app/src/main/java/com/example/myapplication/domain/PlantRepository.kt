package com.example.myapplication.domain

import androidx.lifecycle.LiveData
import com.example.myapplication.Plant

interface PlantRepository {
    fun AddList(plant: Plant)

    fun EditList(plant: Plant)

    fun GetList() : LiveData<List<Plant>>

    fun RemoveList(plant: Plant)

    fun GetIdList(PlantId : Int) : Plant
}
package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.PlantRepository
import java.lang.RuntimeException

object DataImpl : PlantRepository{
    private val liveData = MutableLiveData<List<Plant>>()
    val PlantList = mutableListOf<Plant>()
    var id = -1
    init {
        for (i in 0 until 4) {
            addPlant(Plant(id++, 0,"Красава", "Krasava"))
        }
    }
    fun addPlant(plant: Plant) {
        PlantList.add(plant)
        updateList()
    }
    fun removePlant (plant: Plant) {
        PlantList.remove(plant)
        updateList()
    }
    fun updateList() {
        liveData.value = PlantList.toList()
    }
    private var Incrementa = 0
    override fun AddList(plant: Plant) {
        if (plant.id == -1){
            plant.id = Incrementa++
        }else {
            plant.id = Incrementa++
        }
        addPlant(plant)
        //PlantList.add(plant)
    }

    override fun EditList(plant: Plant) {
        val oldElement = GetIdList(plant.id)
        removePlant(oldElement)
        //PlantList.remove(plant)
        AddList(plant)
    }

    override fun GetList(): LiveData<List<Plant>> {
        return liveData
    }

    override fun RemoveList(plant: Plant) {
        //PlantList.remove(plant)
        removePlant(plant)
    }

    override fun GetIdList(PlantId: Int): Plant {
        return PlantList.find { it.id == PlantId} ?: throw RuntimeException("fgdfg")
    }
}
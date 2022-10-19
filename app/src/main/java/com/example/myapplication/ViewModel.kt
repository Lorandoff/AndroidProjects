package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.*

class MainViewModel : ViewModel() {
    val data = DataImpl
    private val addListUseCase = AddListUseCase(DataImpl)
    private val editListUseCase = EditListUseCase(DataImpl)
    private val removeListUseCase = RemoveListUseCase(DataImpl)
    private val getListUseCase = GetListUseCase(DataImpl)
    private val getIdUseCase = GetIdUseCase(DataImpl)
    var PlantList = getListUseCase.GetList()
    fun addPlant(plant: Plant){
        data.addPlant(plant)
    }
    fun removePlant(plant: Plant) {
        data.removePlant(plant)
       // getList()
    }
    //fun getList() {
    //    val list = getListUseCase.GetList()
    //    PlantList.value = list
    //}
}
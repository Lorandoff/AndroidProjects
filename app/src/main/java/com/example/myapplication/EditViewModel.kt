package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.*
import java.lang.Exception

class EditViewModel : ViewModel() {
    val data = DataImpl
    private val _errorDataInfo = MutableLiveData<Boolean>()
    val errorDataInfoLV : LiveData<Boolean>
        get() = _errorDataInfo
    private val _errorDataInfoDesc = MutableLiveData<Boolean>()
    val errorDataInfoDescLV : LiveData<Boolean>
        get() = _errorDataInfoDesc
    private val _resultInfo = MutableLiveData<Unit>()
    val resultInfo : LiveData<Unit>
        get() = _resultInfo
    private val _Plantitem = MutableLiveData<Plant>()
    val PlantItem : LiveData<Plant>
        get() = _Plantitem
    private val addListUseCase = AddListUseCase(DataImpl)
    private val editListUseCase = EditListUseCase(DataImpl)
    private val removeListUseCase = RemoveListUseCase(DataImpl)
    private val getListUseCase = GetListUseCase(DataImpl)
    private val getIdUseCase = GetIdUseCase(DataImpl)
    val PlantList = getListUseCase.GetList()
    fun editPlant(Name : String?, idPhoto : String?,  desc : String?){
        val name = ParseName(Name)
        //val id = ParseId(id)
        val desc = ParseName(desc)
        val idphoto = ParseId(idPhoto)
        var valid = validateInput(name, desc)
        if(valid) {
            _Plantitem.value?.let {
                val item = it.copy(imageId = idphoto, title = name, desc = desc)
                editListUseCase.EditList(item)//Plant(id, idphoto, name, desc))
                finishWork()
            }
        }
    }
    fun getItem(Plantid : Int) {
        val item = getIdUseCase.GetIdList(Plantid)
        _Plantitem.value = item
    }
    fun addPlantItem(Name : String?, idPhoto : String?, desc : String?) {
        val name = ParseName(Name)
        //val id = ParseId(id)
        val desc = ParseName(desc)
        val idphoto = ParseId(idPhoto)
        var valid = validateInput(name, desc)
        if (valid) {
            addListUseCase.AddList(Plant(imageId = idphoto, title = name, desc = desc))
            finishWork()
        }
    }
    private fun ParseName(name : String?) : String{
        return name?.trim() ?: ""
    }
    private fun ParseId(id : String?) : Int {
        return try {
            id?.trim()?.toInt() ?: 0
        }catch (e : Exception) {
            0
        }
    }
    private fun validateInput(name: String, desc: String) : Boolean {
        var result = true
        if (name.isBlank() ) {
            _errorDataInfo.value = true
            result = false
        }
        if (desc.isBlank()) {
            _errorDataInfoDesc.value = true
            result = false
        }
        return result
    }
    fun resErrorData() {
        _errorDataInfo.value = false
    }
    fun resErrorDesc() {
        _errorDataInfoDesc.value = false
    }
    fun finishWork() {
        _resultInfo.value = Unit
    }
    //fun removePlant(plant: Plant) {
    //    data.removePlant(plant)
       // getList()
    //}
    //fun getList() {
    //    val list = getListUseCase.GetList()
    //    PlantList.value = list
    //}
}
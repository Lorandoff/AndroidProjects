package com.example.myapplication

import java.io.Serializable

data class Plant(var id: Int = -1, val imageId:Int, val title:String, val desc:String) : Serializable{

}
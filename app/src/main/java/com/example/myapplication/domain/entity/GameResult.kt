package com.example.myapplication.domain.entity

import android.os.Parcelable
import com.example.myapplication.domain.entity.GameInterface
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner : Boolean,
    val countofRightAnswer : Int,
    val countofAnswer : Int,
    val gameSettings : GameInterface
) : Parcelable{
}
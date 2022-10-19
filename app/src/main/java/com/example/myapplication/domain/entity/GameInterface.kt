package com.example.myapplication.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameInterface(
    val maxSumValue : Int,
    val minCountofRightAnswer : Int,
    val minPercentofRightAnswer : Int,
    val gameTimeinSeconds : Int
) : Parcelable {
}
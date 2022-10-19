package com.example.myapplication.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Levels : Parcelable{
    TEST, EASY, NORMAL, HARD
}
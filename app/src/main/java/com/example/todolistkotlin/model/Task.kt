package com.example.todolistkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(val id:Int,
                val task:String,
                val review:String?
                ):Parcelable

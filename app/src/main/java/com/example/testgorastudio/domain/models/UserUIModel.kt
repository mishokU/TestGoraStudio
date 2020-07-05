package com.example.testgorastudio.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserUIModel(
    val id : Int,
    val name : String
) : Parcelable
package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId : Int,
    val courseNumber: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val courseDescription: Int,
    val creditUnits: Int
)
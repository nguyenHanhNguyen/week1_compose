package com.example.androiddevchallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(val name: String, val breed: String, val gender: String, val desc: String, val avatar: Int) :
    Parcelable
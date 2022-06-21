package com.example.pizzaorderingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int = 0,
    var name: String = "",
    var address: String = "",
)
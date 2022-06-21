package com.example.pizzaorderingapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CustomerWithPizzas (
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "customerId"
    )

    val pizza: List<Pizza>,
)

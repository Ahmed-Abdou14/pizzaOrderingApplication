package com.example.pizzaorderingapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pizzaorderingapp.data.local.entity.Customer

@Entity(
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        //THE ID OF THE PEARENT
        parentColumns =["customerId"],//????????
        //the foreign key
        childColumns = ["customerId"],
        onDelete = ForeignKey.CASCADE ,
        onUpdate = ForeignKey.CASCADE ,
    )]
)
class Pizza(
    @PrimaryKey(autoGenerate = true)
    var pizzaID: Int = 0,
    var name: String = "",
    var size: String = "",
    @ColumnInfo(index = true)
    var customerId: String = "",

)
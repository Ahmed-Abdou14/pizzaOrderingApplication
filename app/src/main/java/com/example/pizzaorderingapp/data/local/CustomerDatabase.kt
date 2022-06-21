package com.example.pizzaorderingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pizzaorderingapp.data.local.entity.Customer
import com.example.pizzaorderingapp.data.local.entity.Pizza


@Database(
    entities = [Customer::class, Pizza::class],
    version = 1,
    exportSchema = false
)
abstract class CustomerDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao//to import the data access object

    //we will get object that has all the implementation
    companion object {
        //is just one object as static
        @Volatile
        private var database: CustomerDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): CustomerDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerDatabase::class.java,
                    "customer_db"
                ).fallbackToDestructiveMigration().build()
            }
            return database as CustomerDatabase
        }
    }
}
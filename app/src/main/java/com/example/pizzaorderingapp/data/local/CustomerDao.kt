package com.example.pizzaorderingapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pizzaorderingapp.data.local.entity.Customer
import com.example.pizzaorderingapp.data.local.entity.CustomerWithPizzas
import com.example.pizzaorderingapp.data.local.entity.Pizza

/*
* Create Data Access Objects (DAO) Interface and the Database classes.
3. Inside the DAO Interface create all the necessary functions and annotations
*  that allow the user to,
a. Get, Add, Update and Delete a specific Account
b. Query Accounts by specific type [All, Current or Saving] (check the demo)
c. Add new transaction*/
@Dao
interface CustomerDao {
    //Get, Add, Update and Delete a specific Account

    @Query("select * from Customer")
    fun getCustomers(): LiveData<List<Customer>>

    /*@Query("select * from Customer where category=:category")
    fun getMoviesByCategory(category: String): LiveData<List<Customer>>*/

    @Query("select * from Customer where customerId=:ID")
    fun getCustomer(ID: Int): LiveData<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)//if duplicated ids will replace them
    suspend fun addCustomer(customer: Customer)

    /*@Update
    suspend fun updateMovie(movie: Customer)*/

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("select * from Customer")
    fun getCustomerWithPizzas(): LiveData<List<CustomerWithPizzas>>

    //c. Add new transaction*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPizza(pizza: Pizza)

    @Query("select * from `Pizza` where customerId=:ID")
    fun getPizzas(ID: Int): LiveData<List<Pizza>>


    @Query("select * from `Pizza` where pizzaID=:ID")
    fun getPizza(ID: Int): LiveData<Pizza>

   /* @Delete
    suspend fun deleteP(pizza: Pizza): Int*/
}
package com.example.pizzaorderingapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.pizzaorderingapp.data.local.CustomerDatabase
import com.example.pizzaorderingapp.data.local.entity.Customer
import com.example.pizzaorderingapp.data.local.entity.CustomerWithPizzas
import com.example.pizzaorderingapp.data.local.entity.Pizza


/**
 * This class contains dummy data. It is your task to replace it with a room database
 */
class CustomerRepository(context: Context){

    private val customerDao by lazy {
        CustomerDatabase
            .getDatabase(context)
            .customerDao()
    }

    fun getCustomers(): LiveData<List<Customer>> = customerDao.getCustomers()
    fun getCustomer(ID: Int): LiveData<Customer> = customerDao.getCustomer(ID)
    suspend fun addCustomer(customer: Customer) = customerDao.addCustomer(customer)
    suspend fun deleteCustomer(customer: Customer) = customerDao.deleteCustomer(customer)
    fun getCustomerWithPizzas(): LiveData<List<CustomerWithPizzas>> = customerDao.getCustomerWithPizzas()
    fun getPizzas(ID: Int): LiveData<List<Pizza>> = customerDao.getPizzas(ID)
    fun getPizza(ID: Int): LiveData<Pizza> = customerDao.getPizza(ID)
    suspend fun addPizza(pizza: Pizza) =customerDao.addPizza(pizza)


}

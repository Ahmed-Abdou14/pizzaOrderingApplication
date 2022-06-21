package com.example.pizzaorderingapp.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pizzaorderingapp.data.local.entity.Customer
import com.example.pizzaorderingapp.data.local.entity.Pizza
import com.example.pizzaorderingapp.data.repository.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(appContext: Application) : AndroidViewModel(appContext) {

    private val customerRepo by lazy { CustomerRepository(appContext) }
    //var currentFilter by mutableStateOf("All")
    var customers : LiveData<List<Customer>> = customerRepo.getCustomers()
    lateinit var selectedCustomer: Customer

    lateinit var selectedPizza: Pizza

   /* init {
        accounts = generateRandomAccounts()
    }*/

    //Todo Update this method, and connect it to the accountRepo
    fun getAllCustomers(): LiveData<List<Customer>> =
        customerRepo.getCustomers()

    //Todo Update this method, and connect it to the accountRepo
    suspend fun getCustomer(ID: Int) = customerRepo.getCustomer(ID)

    //Todo Update this method, this was done just to help you with the design
    fun addCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepo.addCustomer(customer)
        }
    }

    //Todo Update this method, this was done just to help you with the design
    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepo.deleteCustomer(customer)
        }
    }
///////////////////////////////////////////
    //Todo Update this method, this was done just to help you with the design
    fun addPizza(pizza: Pizza) {
        viewModelScope.launch(Dispatchers.IO) {
            /*val movie = movies.value?.find { it.movieId == rating.movieId.toInt()}
                /*if (transaction.type == "Withdraw") {
                    acc!!.balance = acc?.balance?.minus(transaction.amount)!!
                } else {
                    acc!!.balance = acc?.balance?.plus(transaction.amount)!!
                }*/
            //update rating avg
            movie?.let { movieRepo.addMovie(movie)}*/
            customerRepo.addPizza(pizza)
        }
    }

    fun getPizzas(customerId: Int): LiveData<List<Pizza>> =
        customerRepo.getPizzas(customerId)

}
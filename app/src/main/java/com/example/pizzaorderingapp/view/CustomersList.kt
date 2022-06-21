package com.example.pizzaorderingapp.view

import android.content.ContentValues
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.view.Dropdown
import com.example.pizzaorderingapp.data.local.entity.Customer
import com.example.pizzaorderingapp.viewmodel.CustomerViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CustomersList(   onCustomerSelected: () -> Unit,) {

    val customerViewModel =
        viewModel<CustomerViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
   /* var selectedFilter by remember {
        mutableStateOf("All")
    }*/
    var customers: LiveData<List<Customer>> =customerViewModel.getAllCustomers()
    val customers1 = customers.observeAsState(listOf()).value

   // accountsViewModel.currentFilter = selectedFilter
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {

        LazyColumn {
           items(customers1){ c ->
               CustomerCard(
                   customer = c,
                   deleteCustomer = {customerViewModel.deleteCustomer(c)},
                   onCustomerSelected = {
                       customerViewModel.selectedCustomer = c
                       onCustomerSelected()
                   }
                   )
               /*
               *                 {
                    movieViewModel.selectedMovie= it
                    onEdit()
                }*/
           }

            }
        }
    }


@Composable
fun CustomerCard(
    customer: Customer,
    deleteCustomer: (customer: Customer) -> Unit,
    onCustomerSelected: () -> Unit,
) {
    Card(
        elevation = 20.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable { onCustomerSelected() },
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            customer.customerId?.let { Text(text = "$it") }
            Column(
            ) {
                customer.name.let { Text(text = "Name: $it") }
                customer.address.let { Text(text = "Address: $it") }
            }
            Column {
                IconButton(onClick = { deleteCustomer(customer) } ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Button")
                }
            }
        }
    }
}
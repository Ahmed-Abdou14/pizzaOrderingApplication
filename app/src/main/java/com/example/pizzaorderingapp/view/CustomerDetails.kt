package com.example.pizzaorderingapp.view

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.example.pizzaorderingapp.data.local.entity.Customer
import com.example.pizzaorderingapp.data.local.entity.Pizza
import com.example.pizzaorderingapp.viewmodel.CustomerViewModel
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import okhttp3.*
import java.io.IOException

//Todo add the navigation
@Composable
fun CustomerDetails( onPizzaEdit: () -> Unit) {

    val customerViewModel =
        viewModel<CustomerViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    var pizzas: LiveData<List<Pizza>> =customerViewModel.getPizzas(customerViewModel.selectedCustomer.customerId)
    val pizza1 = pizzas.observeAsState(listOf()).value
    /*  val p = Payment(100,mode = "Cheque",date = LocalDate.parse("2018-12-12"))
      val payments = listOf<Payment>(p)
     // val selectedInvoice = appViewModel.selectedInvoice
   val selectedInvoice = Invoice(1,"111" ,1222.0,startDate = null, dueDate = null)
  //        "Rayan", "123123")*/
    // ToDo: again
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
//        .offset(y = 200.dp),
        ,
        elevation = 16.dp) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            customerViewModel.selectedCustomer.apply {
                Text(text = "Id : $customerId", Modifier.padding(10.dp))
                Text(text = "name: $name", Modifier.padding(10.dp))
                Text(text = "Address: $address",
                    Modifier.padding(10.dp))

                Column(){
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        items(pizza1) { p ->
                            PizzaCard(
                                p,
                                onPizzaEdit = {customerViewModel.selectedPizza = p
                                onPizzaEdit()}
                            )
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun PizzaCard(pizza: Pizza, onPizzaEdit: () -> Unit) {
    Card(
        elevation = 10.dp,
        backgroundColor = Color.White,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            // .clickable { onInvoiceSelected() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f),
            ) {
                Text(text = "Pizza id : ${pizza.pizzaID}")
                Text(text = "Pizza name: ${pizza.name}")
                Text(text = "Pizza size: ${pizza.size}")
            }
            ///////////////////////
            Row(){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .clickable { onPizzaEdit() }
                        .size(40.dp)
                )
            }
        }
    }
}


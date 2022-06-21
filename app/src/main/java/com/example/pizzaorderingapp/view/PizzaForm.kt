package com.example.pizzaorderingapp.view

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.room.ColumnInfo
import com.example.movies.view.Dropdown
import com.example.movies.view.Screen
import com.example.pizzaorderingapp.data.local.entity.Pizza
import com.example.pizzaorderingapp.viewmodel.CustomerViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun PizzaForm(navHostController: NavHostController) {
    val customerViewModel =
        viewModel<CustomerViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val selectedPizza = customerViewModel.selectedPizza

    var customerExpandable by remember {
        mutableStateOf(false)
    }

    var pizzaId by remember {
        mutableStateOf(selectedPizza.pizzaID)
    }
    var pizzaName by remember {
        mutableStateOf(selectedPizza.name)
    }
    var pizzaSize by remember {
        mutableStateOf(selectedPizza.size)
    }
    var customerId by remember {
        mutableStateOf(selectedPizza.customerId)
    }

    var customerName by remember {
        mutableStateOf("")
    }

    val pizzaSizes = listOf("Large", "Medium", "Small")
    val pizzaNames = listOf("Veggie", "Pepperoni", "Margherita")

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(65.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                text = if (selectedPizza.pizzaID != 0) "Edit Pizza" else "Add Pizza",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Divider()
            //Select customer///////////////////////////////////////

            Row(
                modifier = Modifier
                    .clickable {
                        customerExpandable = !customerExpandable
                    }
                    .fillMaxWidth()


            ) {
                Text(text = "Select Customer")
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "type"
                )
            }
            DropdownMenu(
                expanded = customerExpandable,
                onDismissRequest = { customerExpandable = false }) {

                customerViewModel.customers.observeAsState(listOf()).value.forEach { c ->
                    DropdownMenuItem(onClick = {
                        customerId = c.customerId.toString()
                        customerName = c.name
                        customerExpandable = false
                    }) {
                        Text(text = "$c", fontWeight = FontWeight.Bold)

                    }
                }
            }
            Column {
                Text(text = "Customer: $customerId $customerName")
            }

            Dropdown(
                label = "Select pizza size",
                options = pizzaSizes,
                selectedOption = pizzaSize,
                onSelectionChange = { it ->
                    pizzaSize = it
                },
            )

            Dropdown(
                label = "Select pizza Name",
                options = pizzaNames,
                selectedOption = pizzaName,
                onSelectionChange = { it ->
                    pizzaName = it
                },
            )


            Button(onClick = {
                if (selectedPizza.pizzaID.toString().isNotEmpty()) {
                    customerViewModel.addPizza(
                        Pizza(
                            pizzaId,
                            pizzaName,
                            pizzaSize,
                            customerId
                        )
                    )
                }
                navHostController.navigate(Screen.CustomersList.route)
            }) {
                Text(text = "Submit")
            }
        }
    }

}

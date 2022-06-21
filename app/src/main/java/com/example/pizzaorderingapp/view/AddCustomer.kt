package com.example.pizzaorderingapp.view

import android.R
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizzaorderingapp.data.local.entity.Customer
import com.example.pizzaorderingapp.viewmodel.CustomerViewModel
import okhttp3.*
import java.io.IOException



@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AddCustomer(onAdd: () -> Unit) {
    val customerViewModel =
        viewModel<CustomerViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val context = LocalContext.current

    var customerId by remember { mutableStateOf(0) }
    var address by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

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
            Text(text = "Add Customer", fontWeight = FontWeight.Bold, fontSize = 26.sp)
            Divider()


            OutlinedTextField(value = "$name",
                onValueChange = { name = it },
                label = { Text(text = "Enter Name") })
            OutlinedTextField(value = "$address",
                onValueChange = { address = it },
                label = { Text(text = "Enter Address") })
            Button(onClick = {
                   customerViewModel.addCustomer(
                       Customer(
                          customerId,
                           name,
                           address,
                       )
                    )
                onAdd()
                }
            ) {
                Text(text = "Add")
            }
        }
    }
}
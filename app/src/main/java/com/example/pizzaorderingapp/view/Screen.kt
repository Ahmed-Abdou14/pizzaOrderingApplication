package com.example.movies.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Money
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object PizzaForm : Screen("PizzaForm", "Pizza Editor", icon = Icons.Outlined.List)
    object CustomersList : Screen("CustomersList", "Customers List", icon = Icons.Outlined.List)
    object AddCustomer :
        Screen(route = "AddCustomer", title = "Add Customer", icon = Icons.Outlined.Add)
    object CustomerDetails :
        Screen(route = "CustomerDetails", title = "Customer Details", icon = Icons.Outlined.List)
}
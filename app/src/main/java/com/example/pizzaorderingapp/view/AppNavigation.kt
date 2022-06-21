package com.example.pizzaorderingapp.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pizzaorderingapp.view.PizzaForm
import com.example.movies.view.Screen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavigation(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = Screen.CustomersList.route) {
        composable(route = Screen.CustomersList.route) {
            CustomersList(onCustomerSelected = {navHostController.navigate(Screen.CustomerDetails.route)})
        }
        composable(route = Screen.AddCustomer.route) {
            AddCustomer(
            onAdd = {
                navHostController.navigate(route = Screen.CustomersList.route)
            })
        }
        composable(route = Screen.PizzaForm.route) {
            PizzaForm(navHostController)
        }
        composable(route = Screen.CustomerDetails.route) {
            CustomerDetails(
                onPizzaEdit = {navHostController.navigate(route = Screen.PizzaForm.route)})
        }
    }
}
package com.example.pizzaorderingapp


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.pizzaorderingapp.ui.theme.PizzaOrderingAppTheme
import com.example.pizzaorderingapp.view.MainScreen

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.example.pizzaorderingapp.BiiIntents.ITEM_NAME
import com.example.pizzaorderingapp.BiiIntents.ITEM_SIZE
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import kotlin.math.log

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "hi i am in constructor of %s")
        run("http://localhost:5001/?y=y")

        setContent {
            PizzaOrderingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.secondaryVariant) {
                    MainScreen()
                }
            }
        }
        intent?.handleIntent()

    }

    private fun Intent.handleIntent() {
        when (action) {
            // When the BII is matched, Intent.Action_VIEW will be used
            Intent.ACTION_VIEW -> handleIntent(data)
            // Otherwise start the app as you would normally do.
            else -> print("intent not a view")
        }
    }


    private fun handleIntent(data: Uri?) {
        val itemName =  intent?.extras?.getString(ITEM_NAME)
        //val itemSize =  intent?.extras?.getString(ITEM_SIZE)

        Log.d("MainActivity", "caught an intent %s")
        if(itemName != null){

        }

        run("http://localhost:5001/?y=y")

    }

    private val client = OkHttpClient()
    private fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
    }
    /*private fun Intent.handleIntent() {
        when (action) {
            // When the BII is matched, Intent.Action_VIEW will be used
            Intent.ACTION_VIEW -> handleIntent(data)
            // Otherwise start the app as you would normally do.
            else -> print("intent not a view")
        }
    }

    private fun handleIntent(data: Uri?) {

    }*/

}


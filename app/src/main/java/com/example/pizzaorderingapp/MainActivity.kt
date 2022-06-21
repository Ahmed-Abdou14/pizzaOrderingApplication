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
import com.example.pizzaorderingapp.BiiIntents.ITEM_NAME
import com.example.pizzaorderingapp.BiiIntents.ITEM_SIZE
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        run("http://localhost:1111/?test=worked")


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


    @RequiresApi(Build.VERSION_CODES.N)
    private fun Intent.handleIntent() {
        when (action) {
            // When the BII is matched, Intent.Action_VIEW will be used
            Intent.ACTION_VIEW -> handleIntent(data)
            // Otherwise start the app as you would normally do.
            else -> print("intent not a view")
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun handleIntent(data: Uri?) {
        val itemName =  intent?.extras?.getString(ITEM_NAME)
        val itemSize =  intent?.extras?.getString(ITEM_SIZE)



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


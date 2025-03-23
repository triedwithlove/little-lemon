package com.koueka.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koueka.littlelemon.navigation.LLNavigation
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
//import com.koueka.littlelemon.composable.Home
//import com.koueka.littlelemon.composable.Profile
//import com.koueka.littlelemon.composable.Onboarding
import com.koueka.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController: NavHostController = rememberNavController()
            LittleLemonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Onboarding()
                    /*Greeting(
                        name = "Karly",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    //Home()
                    //Profile()
                    Box (modifier = Modifier.fillMaxSize().padding(0.dp)) {
                        LLNavigation(navHostController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}
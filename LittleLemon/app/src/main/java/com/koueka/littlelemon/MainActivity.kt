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
import androidx.lifecycle.lifecycleScope
import com.koueka.littlelemon.navigation.LLNavigation
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.koueka.littlelemon.repository.MenuItemNetwork
import com.koueka.littlelemon.repository.MenuNetwork
import com.koueka.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController: NavHostController = rememberNavController()
            LittleLemonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box (modifier = Modifier.fillMaxSize().padding(0.dp)) {
                        LLNavigation(navHostController)
                    }
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            android.util.Log.d("MainActivity", "onCreate call fetchMenu()")
            var menuItems = fetchMenuFromNetwork()
            android.util.Log.d("MainActivity", "onCreate list zise =" + menuItems.size)
            android.util.Log.d("MainActivity", "onCreate call saveMenuToDatabase()")
        }

    }


    private suspend fun fetchMenuFromNetwork(): List<MenuItemNetwork> {
        val urlStr = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val menuNetworkInstance: MenuNetwork = httpClient
            .get(urlStr)
            .body<MenuNetwork>()
        android.util.Log.d("MainActivity", menuNetworkInstance.toString())
        return menuNetworkInstance.menu ?: listOf()
    }
}

/*
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
*/
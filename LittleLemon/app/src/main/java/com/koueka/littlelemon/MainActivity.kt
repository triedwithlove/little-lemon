package com.koueka.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.koueka.littlelemon.navigation.LLNavigation
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.koueka.littlelemon.repository.MenuItemNetwork
import com.koueka.littlelemon.repository.MenuNetwork
import com.koueka.littlelemon.repository.data.AppDatabase
import com.koueka.littlelemon.repository.data.MenuItemRoom
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

    private val mHttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val mDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController: NavHostController = rememberNavController()
            LittleLemonTheme {
                // add databaseMenuItems code here
                val databaseMenuItems by mDatabase.menuItemDao().getAll()
                    .observeAsState(emptyList<MenuItemRoom>())

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box (modifier = Modifier.fillMaxSize().padding(0.dp)) {
                        LLNavigation(navHostController, databaseMenuItems)
                    }
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (mDatabase.menuItemDao().isEmpty()) {
                android.util.Log.d("MainActivity", "onCreate call fetchMenu()")
                var menuItems = fetchMenuFromNetwork()
                android.util.Log.d("MainActivity", "onCreate list zise =" + menuItems.size)
                android.util.Log.d("MainActivity", "onCreate call saveMenuToDatabase()")
                saveMenuToDatabase(menuItems)
            } else {
                android.util.Log.d("MainActivity", "onCreate DB not empty")
            }
        }

    }


    private suspend fun fetchMenuFromNetwork(): List<MenuItemNetwork> {
        val urlStr = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val menuNetworkInstance: MenuNetwork = mHttpClient
            .get(urlStr)
            .body<MenuNetwork>()
        android.util.Log.d("MainActivity", menuNetworkInstance.toString())
        return menuNetworkInstance.menu ?: listOf()
    }


    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        mDatabase.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
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
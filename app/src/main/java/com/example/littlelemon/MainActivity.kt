package com.example.littlelemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonApp()
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LittleLemonApp() {
    var searchPhrase by remember { mutableStateOf("") }
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    var menuItems by remember { mutableStateOf(emptyList<MenuItemEntity>()) }

    // اجرای عملیات شبکه در یک Coroutine
    scope.launch {
        val client = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
        try {
            // فراخوانی API
            val response: HttpResponse = client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            val menuData = response.body<MenuNetworkData>()
            menuItems = menuData.menu.map { menuItem ->
                MenuItemEntity(
                    id = menuItem.id,
                    title = menuItem.title,
                    description = menuItem.description,
                    price = menuItem.price,
                    image = menuItem.image
                )
            }

            // ذخیره داده‌ها در دیتابیس (اگر menuDao موجود باشد)
            // menuDao.insertMenuItems(menuItems)
        } catch (e: Exception) {
            // مدیریت خطا
            Log.e("MainActivity", "Error fetching menu: ${e.message}")
        } finally {
            client.close()
        }
    }

    Column {
        // فیلد جستجو
        TextField(
            value = searchPhrase,
            onValueChange = { searchPhrase = it },
            placeholder = { Text("Enter Search Phrase") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        // نمایش آیتم‌های منو
        MenuItems(menuItems = menuItems, searchPhrase = searchPhrase)
    }

    // تنظیمات ناوبری
    MyNavigation(navController = navController)
}

@Composable
fun MenuItems(menuItems: List<MenuItemEntity>, searchPhrase: String) {
    val filteredMenuItems = if (searchPhrase.isNotBlank()) {
        menuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
    } else {
        menuItems
    }

    LazyColumn {
        items(filteredMenuItems) { item ->
            MenuItemCard(item)
        }
    }
}

@Composable
fun MenuItemCard(item: MenuItemEntity) {
    Card {
        Column {
            Text(text = item.title)
            Text(text = item.description)
            Text(text = "$${item.price}")
        }
    }
}
@Composable
fun  Search(){
    TextField(
value = searchPhrase,
onValueChange = { searchPhrase = it },
placeholder = { Text("Enter Search Phrase") },
leadingIcon = {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search Icon"
    )
},
modifier = Modifier.fillMaxWidth()
)}
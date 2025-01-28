package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.lang.reflect.Modifier

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier =androidx.compose.ui.Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = androidx.compose.ui.Modifier.size(100.dp)
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        // Profile Button
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = androidx.compose.ui.Modifier
                .size(50.dp)
                .clickable {
                    navController.navigate("profile")
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
    )
}
@Composable
fun HeroSection() {
    Column {
        Text("Restaurant name: Little Lemon")
        Text("City: Chicago")
        Text("Description: We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist")
        Image(painter = painterResource(id = R.drawable.hero_image), contentDescription = "Hero Image")
    }
}
@Composable
fun MenuItems(menuItems: List<MenuItemEntity>) {
    Column {
        menuItems.forEach { item ->
            MenuItemRow(item)
        }
    }
}

@Composable
fun MenuItemRow(menuItem: MenuItemEntity) {
    Column {
        Text(menuItem.title)
        Text(menuItem.description)
        Text("\$${menuItem.price}")
        GlideImage(model = menuItem.image, contentDescription = menuItem.title)
    }
}
@Composable
fun GlideImage(model: String, contentDescription: String) {
    GlideImage(
        model = model,
        contentDescription = contentDescription
    )
}


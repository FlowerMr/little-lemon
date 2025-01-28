package com.example.littlelemon

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class MenuNetworkData(
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String
)

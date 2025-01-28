package com.example.littlelemon

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String
)

@Dao
interface MenuDao {
    @Insert
    suspend fun insertMenuItems(items: List<MenuItemEntity>)

    @Query("SELECT * FROM menu_items")
    suspend fun getMenuItems(): List<MenuItemEntity>
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}

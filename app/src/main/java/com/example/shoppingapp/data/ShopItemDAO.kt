package com.example.shoppingapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingapp.domain.ShopItem

@Dao
interface ShopItemDAO {
    @Query ("SELECT * FROM shopitem")
    fun getAll(): List<ShopItem>

    @Insert
    fun insertAll(vararg shopItems: ShopItem)

}
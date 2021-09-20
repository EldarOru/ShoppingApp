package com.example.shoppingapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopItem(
    @ColumnInfo (name = "name")
    val name: String,
    @ColumnInfo (name = "count")
    val count: Int,
    @ColumnInfo (name = "enabled")
    val enabled: Boolean,
    @PrimaryKey (autoGenerate = false)
    var id: Int = UNDEFINED_ID
){

    companion object{
        const val UNDEFINED_ID = -1
    }
}



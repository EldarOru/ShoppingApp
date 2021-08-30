package com.example.shoppingapp.domain

import androidx.lifecycle.LiveData

interface ShopItemRepository {
    fun addShopItem(shopItem:ShopItem)
    fun deleteShopItem(shopItem:ShopItem)
    fun changeShopItem(shopItem:ShopItem)
    fun getShopItem(shopItemID: Int):ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}
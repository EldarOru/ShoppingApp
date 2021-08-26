package com.example.shoppingapp.domain

interface ShopItemRepository {
    fun addShopItem(shopItem:ShopItem)
    fun deleteShopItem(shopItem:ShopItem)
    fun changeShopItem(shopItem:ShopItem)
    fun getShopItem(shopItemID: Int):ShopItem
    fun getShopList(): List<ShopItem>
}
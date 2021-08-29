package com.example.shoppingapp.data

import com.example.shoppingapp.domain.ShopItem
import com.example.shoppingapp.domain.ShopItemRepository
import java.util.*

object ShopListRepositoryImpl: ShopItemRepository {

    private val shopList:MutableList<ShopItem> = mutableListOf()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10){
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun changeShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemID: Int): ShopItem{
        var shopItem:ShopItem? = null
        for (ob in shopList){
            if (ob.id == shopItemID){
                shopItem == ob
            }
        }
        return shopItem ?: throw Exception ("Element with id $shopItemID is not existed")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList
    }
}
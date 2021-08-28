package com.example.shoppingapp.data

import com.example.shoppingapp.domain.ShopItem
import com.example.shoppingapp.domain.ShopItemRepository
import java.util.*

object ShopListRepositoryImpl: ShopItemRepository {
    private val shopList:MutableList<ShopItem> = ArrayList()
    override fun addShopItem(shopItem: ShopItem) {
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun changeShopItem(shopItem: ShopItem) {

    }

    TODO()
    override fun getShopItem(shopItemID: Int): ShopItem {
        for(shopItem in shopList){
            if (shopItem.id == shopItemID){
                return shopItem
            }else throw RuntimeException ("")
        }
        return null
    }

    override fun getShopList(): List<ShopItem> {
        return shopList
    }
}
package com.example.shoppingapp.domain

class GetShopItemUseCase (private val shopItemRepository: ShopItemRepository) {

    fun getShopItem(shopItemID: Int):ShopItem{
         return shopItemRepository.getShopItem(shopItemID)
    }

}
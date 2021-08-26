package com.example.shoppingapp.domain

class ChangeShopItemUseCase (private val shopItemRepository: ShopItemRepository) {

    fun changeShopItem(shopItem:ShopItem){
        shopItemRepository.changeShopItem(shopItem)

    }

}
package com.example.shoppingapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppingapp.domain.ShopItem

class ShopListDiffCallback(
        private val oldList: List<ShopItem>,
        private val newList: List<ShopItem>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    //если id разные, то и объекты разные
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    //сравнивает старый и новый объекты
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}
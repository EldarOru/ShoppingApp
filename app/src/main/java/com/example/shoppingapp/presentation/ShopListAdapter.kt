package com.example.shoppingapp.presentation

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.ShopItem
import java.lang.RuntimeException

//Если сделать определенные View другого цвета (исходя из if), то при пролистывании вниз
//некоторые View будут другого цвета, хотя не проходят if. Это связано с тем, что RecyclerView переиспользует
//прошлые View
class ShopListAdapter() : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    /*
    var shopList = listOf<ShopItem>()
    set(value) {
        //field - изначальное значениe, value - присвоенное
        val callback = ShopListDiffCallback(shopList, value)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        field = value
        //notifyDataSetChange полностью обновляет список
        //а мы сделали так, чтобы обновлялись только меняющие поля
    }
     */

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    //Как создавать View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType){
            DISABLED_NUM -> R.layout.item_shop_disabled
            ENABLED_NUM -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return ShopItemViewHolder(view)
    }

    //Как вставить значения внутри View
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    //определяет enable у shopItem, на основании которого в onCreateViewHolder выбирается макет
    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled){
            ENABLED_NUM
        }else DISABLED_NUM
    }

    companion object {
        const val ENABLED_NUM = 1
        const val DISABLED_NUM = 2
    }
}
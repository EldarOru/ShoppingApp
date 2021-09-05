package com.example.shoppingapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.ShopItem
import java.lang.RuntimeException

//Если сделать определенные View другого цвета (исходя из if), то при пролистывании вниз
//некоторые View будут другого цвета, хотя не проходят if. Это связано с тем, что RecyclerView переиспользует
//прошлые View
class ShopListAdapter() : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

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
        val shopItem = shopList[position]
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

    override fun getItemCount(): Int {
        return shopList.size
    }

    //определяет enable у shopItem, на основании которого в onCreateViewHolder выбирается макет
    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enabled){
            ENABLED_NUM
        }else DISABLED_NUM
    }

    //Класс, который хранит View
    //Чтобы постоянно не вызывать findViewById
    //Есть pull с view
    //Всего создается n-ое количество view, где k<n будет видно на экране,
    //а остальные будут вне экрана
    class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    companion object {
        const val ENABLED_NUM = 1
        const val DISABLED_NUM = 2
    }
}
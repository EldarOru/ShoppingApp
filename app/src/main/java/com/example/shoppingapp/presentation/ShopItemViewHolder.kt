package com.example.shoppingapp.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import org.w3c.dom.Text

//Класс, который хранит View
//Чтобы постоянно не вызывать findViewById
//Есть pull с view
//Всего создается n-ое количество view, где k<n будет видно на экране,
//а остальные будут вне экрана
class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
    val tvName: TextView = view.findViewById(R.id.tv_name)
    val tvCount: TextView = view.findViewById(R.id.tv_count)
}
package com.onay.minishop.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.onay.minishop.R
import com.onay.minishop.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    class ShopItemViewHolder(val view : View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    var ShopList = listOf<ShopItem>()
    set(value){
        field = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_enabled,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = ShopList[position]
        val status = if(shopItem.enabled){
            "Active"
        }
        else{
            "Not Active"
        }

        viewHolder.view.setOnLongClickListener {
            true
        }
        if(shopItem.enabled){
            viewHolder.tvName.text =" ${ shopItem.name } ${status }"
            viewHolder.tvCount.text = shopItem.count.toString()
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context , android.R.color.holo_red_light))
        }
        /*
        1 способ
        else
        {
            viewHolder.tvName.text =""
            viewHolder.tvCount.text = ""
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context , android.R.color.white))
        }
        решение бага в котором существующие view была переисползывана но число 2 может в 17 месте
         */
    }

    override fun getItemCount(): Int {
        return ShopList.size
    }
    /*
    override fun getItemViewType(position: Int): Int {
       return  position
    }
    не стоит исползывать
     */

    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text =""
        viewHolder.tvCount.text = ""
        viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context , android.R.color.white))
        //установлены значения по умолчанию

    }

}
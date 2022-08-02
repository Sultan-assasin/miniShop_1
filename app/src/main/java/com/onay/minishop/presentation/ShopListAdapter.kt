package com.onay.minishop.presentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.onay.minishop.R
import com.onay.minishop.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var count = 0
    class ShopItemViewHolder(val view : View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)

    }

    var ShopList = listOf<ShopItem>()
    set(value){
        field = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder, count: ${++count}")
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = ShopList[position]
        viewHolder.view.setOnLongClickListener {
            true
        }
            viewHolder.tvName.text =shopItem.name
            viewHolder.tvCount.text = shopItem.count.toString()

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

    override fun getItemViewType(position: Int): Int {
        val item = ShopList[position]
        return if(item.enabled){
            VIEW_TYPE_ENABLED
        }else{
            VIEW_TYPE_DISABLED
        }
    }

    /*
    не стоит исползывать
    причина
    когда мы вызывали onBindviewhohlder он проверяет совпадает ли viewholder если нет то position .
    но как так мы определяем position каждый раз viewtype будет у всех разных и он заново создаст
    новый обьект и мы также вернулись первоначалной проблеме . сколько объектов столько view создастся
    заново . но они будут создаваться при скроле а не сразу . вот и вся разница

     */

    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text =""
        viewHolder.tvCount.text = ""

        //установлены значения по умолчанию

    }
    companion object {
        val VIEW_TYPE_ENABLED = 0
        val VIEW_TYPE_DISABLED = 1

        const val  MAX_POOL_SIZE = 20
    }

}
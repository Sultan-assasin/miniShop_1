package com.onay.minishop.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.onay.minishop.R
import com.onay.minishop.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter : ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupReciclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //здесь мы подписались на LiveData , а внутри будет реализация отоброжения элеентов
        viewModel.shopList.observe(this){
            adapter.ShopList = it
        }
    }

    private fun setupReciclerView(){
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = ShopListAdapter()
        rvShopList.adapter = adapter
    }
}
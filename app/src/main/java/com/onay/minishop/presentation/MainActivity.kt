package com.onay.minishop.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.onay.minishop.R


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter : ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //здесь мы подписались на LiveData , а внутри будет реализация отоброжения элеентов
        viewModel.shopList.observe(this){
            shopListAdapter.shopList = it
        }
    }


    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)

        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter // баг был исправлен
           recycledViewPool.setMaxRecycledViews(
               ShopListAdapter.VIEW_TYPE_ENABLED,
               ShopListAdapter.MAX_POOL_SIZE
           )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )

        }
        setupLongClickListener()
        setupClickListener()

        }
    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
    private fun setupClickListener(){
        shopListAdapter.onShopItemClickListener = {
            Log.d("MainActivity" , it.toString())

        }
    }
}


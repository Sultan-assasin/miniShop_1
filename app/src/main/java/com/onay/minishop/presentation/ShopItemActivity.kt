package com.onay.minishop.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onay.minishop.R

class ShopItemActivity : AppCompatActivity() {
    private lateinit var shopviewModel: ShopItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)

    }
}
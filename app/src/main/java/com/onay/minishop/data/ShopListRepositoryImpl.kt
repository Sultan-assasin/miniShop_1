package com.onay.minishop.data

import com.onay.minishop.domain.ShopItem
import com.onay.minishop.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl : ShopListRepository {



    // object is single tone

    private val shopList  = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }

        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val OldElement = getShopItem(shopItem.id)
        shopList.remove(OldElement)
        shopList.add(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw  RuntimeException("Element with $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}
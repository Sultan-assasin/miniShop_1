package com.onay.minishop.data

import com.onay.minishop.domain.ShopItem
import com.onay.minishop.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl : ShopListRepository {
// data зависит от репоситория  и все о нем знает и наборот
    // data слой отвечает за базу данных
    // data слой предоставляет конкретную реализацию репозитория


    // object is single tone

    private val shopList  = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    //   что бы все методы работали и добавились данные вызываем init

    init {
        for (i in 0 until 10){
            val item = ShopItem("Name $i " , i , true)
            addShopItem(item)
        }
    }

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
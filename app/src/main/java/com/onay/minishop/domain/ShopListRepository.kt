package com.onay.minishop.domain

interface ShopListRepository {

   // он должен уметь то что нало нашим use кейсам

    // он умеет работать с базой данных

    fun addShopItem(shopItem : ShopItem)

    fun deleteShopItem(shopItem : ShopItem)

    fun editShopItem(shopItem : ShopItem)

    fun getShopItem(shopItemId: Int) : ShopItem

    fun getShopList() : List<ShopItem>
}
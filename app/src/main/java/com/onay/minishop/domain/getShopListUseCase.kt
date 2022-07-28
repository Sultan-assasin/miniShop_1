package com.onay.minishop.domain

class getShopListUseCase(private val shopListRepository: ShopListRepository)  {
    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }
}
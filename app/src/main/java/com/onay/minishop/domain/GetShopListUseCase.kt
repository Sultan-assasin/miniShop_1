package com.onay.minishop.domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository)  {
    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }
}
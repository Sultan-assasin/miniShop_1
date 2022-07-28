package com.onay.minishop.domain

class deleteShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopList(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}
package com.onay.minishop.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopList(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}
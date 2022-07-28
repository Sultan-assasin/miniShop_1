package com.onay.minishop.domain

class editShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopList(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}
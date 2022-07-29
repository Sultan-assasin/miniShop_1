package com.onay.minishop.domain
// добавление в конструктордля реализаций
class AddShopListUseCase(private val shopListRepository: ShopListRepository) {

    // private val переменная репозитория

    // наш domain слой ни от чего не зависим
    // для реализациий цели исползуеться репоситорий
    // репоситории черный ящик который умеет раотать с данными  и внутренная устройство его реализация тветственна к data слою



    fun addShopList(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}
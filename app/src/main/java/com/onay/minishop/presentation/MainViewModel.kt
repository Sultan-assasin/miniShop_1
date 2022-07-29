package com.onay.minishop.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onay.minishop.data.ShopListRepositoryImpl // здесь мы исползуем data слой и пока она не сответствует к предстовлениям
import com.onay.minishop.domain.DeleteShopItemUseCase
import com.onay.minishop.domain.EditShopItemUseCase
import com.onay.minishop.domain.GetShopListUseCase
import com.onay.minishop.domain.ShopItem

// Presentation слой отвечает за отоброжение данных и вхаимодействие с пользывателем
// Методы бизнес логики вызываются из useCase (interactors)
//с doomain слоем взаймодействует viewModel
// в чистой архитектуре presentation слой знает все domain слое б но ничего не знеат о data слое
//



// если context нужен то мы наследуемься от AndroidViewModel a если не нужен то от ViewModel
class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    // мы подписываемься на него с activity либо с фрагметов что бы данные отобразились в реальном времени
    val shopList = MutableLiveData<List<ShopItem>>() // череез этот код мы подписываемься на все изминения
        // разница между MutableLiveData и LiveData в том что в первом случае мы можем сами создавать элементы

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopListUseCase = EditShopItemUseCase(repository)

    fun getShopList(){
        val list = getShopListUseCase.getShopList() // получаем данные с нашего getShopListUseCase и отоброжаем на livedata
        shopList.value = list // value можно вызвать только с главного потока а postValue можно вызвать на любом месте
    }
    fun deleteShopItem(shopItem: ShopItem){
       deleteShopItemUseCase.deleteShopList(shopItem)
        getShopList()
    }
    fun editShopItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = ! shopItem.enabled)
        editShopListUseCase.editShopItem(newItem)
        getShopList()
    }


}
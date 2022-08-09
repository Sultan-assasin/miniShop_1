package com.onay.minishop.presentation

import androidx.lifecycle.ViewModel
import com.onay.minishop.data.ShopListRepositoryImpl
import com.onay.minishop.domain.AddShopItemUseCase
import com.onay.minishop.domain.EditShopItemUseCase
import com.onay.minishop.domain.GetShopItemUseCase
import com.onay.minishop.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)

    fun editShopItem(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem)
    }
    fun getShopItem(shopItemId: Int){
        val item = getShopItemUseCase.getShopItem(shopItemId = shopItemId)
    }
    fun addShopItem(inputName : String? , inputCount : String?){
       val name =  parseString(inputName)
        val count = parseInt(inputCount)
        val fieldValid = validateInput(name , count)
        if(fieldValid) {
            val shopItem = ShopItem(name , count , true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun parseString(inputName : String?): String {
        return inputName?.trim() ?: ""
    }
    fun parseInt(inputCount : String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name : String , count : Int ) : Boolean{
        var result = true
        if(name.isBlank()){
            //TODO: show error input name
             result = false
        }
        if (count<= 0){
            //TODO: show error input count
            result = false
        }
        return result
    }

}
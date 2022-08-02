package com.onay.minishop.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onay.minishop.domain.ShopItem
import com.onay.minishop.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {
// data зависит от репоситория  и все о нем знает и наборот
    // data слой отвечает за базу данных
    // data слой предоставляет конкретную реализацию репозитория

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    // object is single tone

    private val shopList  = sortedSetOf<ShopItem>(Comparator<ShopItem> { p0, p1 -> p0.id.compareTo(p1.id) }) // сортировка по id

    private var autoIncrementId = 0

    //   что бы все методы работали и добавились данные вызываем init

    init {
        for (i in 0 until 1000){
            val item = ShopItem("Name $i " , i , Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }

        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val OldElement = getShopItem(shopItem.id)
        shopList.remove(OldElement)
        addShopItem(shopItem)


    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw  RuntimeException("Element with $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }
    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}
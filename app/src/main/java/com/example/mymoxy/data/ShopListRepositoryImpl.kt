package com.example.mymoxy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymoxy.presentarion.ShopListAdapter
import com.sumin.shoppinglist.domain.ShopItem
import com.sumin.shoppinglist.domain.ShopListRepository
import org.koin.core.definition.indexKey

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = mutableListOf<ShopItem> ()
    private val shopList = mutableListOf<ShopItem>()
    private lateinit var item:ShopItem
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            if(i in 3..4) {
                 item = ShopItem("Name $i", i, true)
            }else{
                 item = ShopItem("Name $i", i, false)
            }
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
       // updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        //updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        val shop = ShopItem(shopItem.name,shopItem.count,shopItem.enabled,shopItem.id)
        shop.enabled=!shop.enabled
        addShopItem(shop)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.sortedBy { it.id }
    }

    override fun getShopList2(): List<ShopItem> {
        shopList.clear()
        autoIncrementId = 0
        for (i in 0 until 10) {
            if(i==1||i==3||i==6) {
                val item = ShopItem("Name $i", i + 1, true)
                addShopItem(item)
            }else{
                val item = ShopItem("Name $i", i , true)
                addShopItem(item)
            }
        }
        return shopList
    }


//    private fun updateList() {
//        shopListLD.value = shopList.toList()
//    }
}

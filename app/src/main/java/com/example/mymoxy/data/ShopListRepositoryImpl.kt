package com.example.mymoxy.data

import android.util.Log
import com.sumin.shoppinglist.domain.ShopItem
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor() : ShopListRepository {

    private val mutableSharedFlowShopList = MutableSharedFlow<List<ShopItem>>(replay = 1)
    private val sharedFlowShopList = mutableSharedFlowShopList.asSharedFlow()
    private val shopList = mutableListOf<ShopItem>()

    private lateinit var item: ShopItem
    private var autoIncrementId = 0

    init {
        shopList.clear()
        Log.d("init","Init")
        for (i in 0 until 100) {
            if (i in 3..4) {
                item = ShopItem("Name $i", i, true)
            } else {
                item = ShopItem("Name $i", i, false)
            }
            initAddData(item)
        }
    }

    private fun initAddData(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        val shop = ShopItem(shopItem.name, shopItem.count, shopItem.enabled, shopItem.id)
        shop.enabled = !shop.enabled
        addShopItem(shop)
        updateList()
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override  fun getShopList(): SharedFlow<List<ShopItem>> {
        return sharedFlowShopList
    }

    override suspend fun initData() {
        updateList()
    }

    override suspend fun getShopList2() {
        shopList.clear()
        autoIncrementId = 0
        for (i in 0 until 10) {
            if (i == 1 || i == 3 || i == 6) {
                val item = ShopItem("Name $i", i + 1, true)
                addShopItem(item)
            } else {
                val item = ShopItem("Name $i", i, true)
                addShopItem(item)
            }
        }
        updateList()
    }

    private suspend fun updateList() {
        mutableSharedFlowShopList.emit(shopList.toList().sortedBy { it.id })
    }
}

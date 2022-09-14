package com.example.mymvvm.data

import com.sumin.shoppinglist.domain.ShopItem
import kotlinx.coroutines.flow.SharedFlow

interface ShopListRepository {


    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): SharedFlow<List<ShopItem>>

    suspend fun initData()

    suspend fun getShopList2()
}

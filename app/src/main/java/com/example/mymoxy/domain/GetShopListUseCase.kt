package com.sumin.shoppinglist.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.coroutineScope

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

  suspend fun getShopList(): List<ShopItem> {
        return coroutineScope {
            shopListRepository.getShopList()}
    }
}

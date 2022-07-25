package com.sumin.shoppinglist.domain

import com.example.mymoxy.data.ShopListRepository
import javax.inject.Inject

interface DeleteShopItemUseCase{
    suspend fun deleteShopItem(shopItem: ShopItem)
}

class DeleteShopItemUseCaseImpl  @Inject constructor(private val shopListRepository: ShopListRepository):DeleteShopItemUseCase {

  override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}

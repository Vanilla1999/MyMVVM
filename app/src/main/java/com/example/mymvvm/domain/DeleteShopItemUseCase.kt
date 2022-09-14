package com.example.mymvvm.domain

import com.example.mymvvm.data.ShopListRepository
import com.sumin.shoppinglist.domain.ShopItem
import javax.inject.Inject

interface DeleteShopItemUseCase{
    suspend fun deleteShopItem(shopItem: ShopItem)
}

class DeleteShopItemUseCaseImpl  @Inject constructor(private val shopListRepository: ShopListRepository):
    DeleteShopItemUseCase {

  override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}

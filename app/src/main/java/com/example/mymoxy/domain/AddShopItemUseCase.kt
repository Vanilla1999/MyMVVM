package com.sumin.shoppinglist.domain

import com.example.mymoxy.data.ShopListRepository
import javax.inject.Inject

interface AddShopItemUseCase{
    suspend fun addShopItem(shopItem: ShopItem)
}

class AddShopItemUseCaseImpl  @Inject constructor(private val shopListRepository: ShopListRepository): AddShopItemUseCase{

   override suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}

package com.sumin.shoppinglist.domain

import com.example.mymvvm.data.ShopListRepository
import javax.inject.Inject

interface EditShopItemUseCase{
    suspend fun editShopItem(shopItem: ShopItem)
}

class EditShopItemUseCaseImpl  @Inject constructor(private val shopListRepository: ShopListRepository):EditShopItemUseCase {

   override suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}

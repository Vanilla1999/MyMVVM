package com.sumin.shoppinglist.domain

import com.example.mymoxy.data.ShopListRepository
import javax.inject.Inject

interface GetShopItemUseCase {
    suspend fun getShopItem(shopItemId: Int): ShopItem

}


class GetShopItemUseCaseImpl @Inject constructor(private val shopListRepository: ShopListRepository) :
    GetShopItemUseCase {

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}

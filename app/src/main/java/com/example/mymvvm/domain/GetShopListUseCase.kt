package com.sumin.shoppinglist.domain

import com.example.mymvvm.data.ShopListRepository
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject


interface GetShopListUseCase {
     fun getShopList(): SharedFlow<List<ShopItem>>
    suspend fun getShopList2()
}

class GetShopListUseCaseImpl @Inject constructor(private val shopListRepository: ShopListRepository) :
    GetShopListUseCase {

    override  fun getShopList(): SharedFlow<List<ShopItem>> {
        return shopListRepository.getShopList()
    }

    override suspend fun getShopList2()  {
        shopListRepository.getShopList2()
    }
}

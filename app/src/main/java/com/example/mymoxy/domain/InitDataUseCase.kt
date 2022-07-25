package com.example.mymoxy.domain

import com.example.mymoxy.data.ShopListRepository
import com.sumin.shoppinglist.domain.GetShopListUseCase
import com.sumin.shoppinglist.domain.ShopItem
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

interface InitDataUseCase {
   suspend fun initData()
}

class InitDataUseCaseImpl @Inject constructor(private val shopListRepository: ShopListRepository) :
    InitDataUseCase {
    override suspend fun initData() {
        shopListRepository.initData()
    }

}
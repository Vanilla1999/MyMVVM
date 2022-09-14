package com.example.mymvvm.domain

import com.example.mymvvm.data.ShopListRepository
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
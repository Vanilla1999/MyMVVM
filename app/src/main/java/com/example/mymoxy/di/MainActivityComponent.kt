package com.example.mymoxy.di

import android.content.Context
import com.example.mymoxy.MainActivity
import com.example.mymoxy.data.ShopListRepository
import com.example.mymoxy.data.ShopListRepositoryImpl
import com.example.mymoxy.domain.InitDataUseCase
import com.example.mymoxy.domain.InitDataUseCaseImpl
import com.example.mymoxy.tools.MainActivityContext
import com.example.mymoxy.tools.MainActivityScope
import com.sumin.shoppinglist.domain.*
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Module
interface BindingModule {

    @MainActivityScope
    @Suppress("FunctionName")
    @Binds
    fun bindsShopListRepo(shopListRepositoryImpl: ShopListRepositoryImpl): ShopListRepository

    @MainActivityScope
    @Suppress("FunctionName")
    @Binds
    fun bindsInitDataUseCase_to_InitDataUseCaseImpl(initDataUseCase: InitDataUseCaseImpl): InitDataUseCase

    @MainActivityScope
    @Suppress("FunctionName")
    @Binds
    fun bindsAddShopItemUseCase_to_AddShopItemUseCaseImpl(addShopItemUseCase: AddShopItemUseCaseImpl): AddShopItemUseCase

    @MainActivityScope
    @Suppress("FunctionName")
    @Binds
    fun bindsDeleteShopItemUseCase_to_DeleteShopItemUseCaseImpl(deleteShopItem: DeleteShopItemUseCaseImpl): DeleteShopItemUseCase

    @MainActivityScope
    @Suppress("FunctionName")
    @Binds
    fun bindsEditItemUseCase_to_EditShopItemUseCaseImpl(addShopItemUseCase: EditShopItemUseCaseImpl): EditShopItemUseCase

    @MainActivityScope
    @Suppress("FunctionName")
    @Binds
    fun bindsGetShopItemUseCase_to_GetShopItemUseCaseImpl(getShopItemUseCase: GetShopItemUseCaseImpl): GetShopItemUseCase

    @MainActivityScope
    @Suppress("FunctionName")
    @Binds
    fun bindsGetShopItemListUseCase_to_GetShopItemListUseCaseImpl(getShopListUseCaseImpl: GetShopListUseCaseImpl): GetShopListUseCase

}

@MainActivityScope
@Component(
    modules = [BindingModule::class]
)
interface MainActvitityComponent {

    fun bindsShopListRepository(): ShopListRepository

    @Suppress("FunctionName")
    fun bindsInitDataUseCase_to_InitDataUseCaseImpl(): InitDataUseCase

    @Suppress("FunctionName")
    fun bindsAddShopItemUseCase_to_AddShopItemUseCaseImpl(): AddShopItemUseCase

    @Suppress("FunctionName")
    fun bindsDeleteShopItemUseCase_to_DeleteShopItemUseCaseImpl(): DeleteShopItemUseCase

    @Suppress("FunctionName")
    fun bindsEditItemUseCase_to_EditShopItemUseCaseImpl(): EditShopItemUseCase

    @Suppress("FunctionName")
    fun bindsGetShopItemUseCase_to_GetShopItemUseCaseImpl(): GetShopItemUseCase

    @Suppress("FunctionName")
    fun bindsGetShopItemListUseCase_to_GetShopItemListUseCaseImpl(): GetShopListUseCase

    @MainActivityContext
    fun getActivity(): Context

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @MainActivityContext context: Context): MainActvitityComponent
    }
}
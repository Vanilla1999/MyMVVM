package com.example.mymoxy.domain

import com.example.mymoxy.data.ShopListRepositoryImpl
import com.example.mymoxy.presentarion.FirstInterface
import com.sumin.shoppinglist.domain.DeleteShopItemUseCase
import com.sumin.shoppinglist.domain.EditShopItemUseCase
import com.sumin.shoppinglist.domain.GetShopListUseCase
import com.sumin.shoppinglist.domain.ShopItem
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenter.ProvidePresenter
import kotlin.coroutines.CoroutineContext

@InjectViewState
class FirstPresenter() : MvpPresenter<FirstInterface>(), CoroutineScope {
    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val jobList = mutableListOf<Job>()

    /**
     * Инициализация Presenter при первом attach View, сохраняем инициализирующее значение. При дальнейших attach Presenter к новым View, этот метод не выполняется.
     */

    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.onInitImage()
        interact(
            {
                getShopListUseCase.getShopList()
            },
            {
                viewState.onInitShopList(it)
            }, {})
    }

    /**
     * Перекрашиваем картинку
     */
    fun recolorImage() {
        viewState.onRecolorImage()
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeList() {
        interact(
            {
                getShopListUseCase.getShopList2()
            },
            {
                viewState.onChengeShopList(it)
            }, {})
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

    private fun <B> interact(
        interaction: suspend () -> B,
        callback: (B) -> Unit,
        callbackError: (Throwable) -> Unit
    ) {
        launch {
            try {
                val job = async(Dispatchers.Default) { interaction()  }
                jobList.add(job)
                val result = job.await()
                withContext(Dispatchers.Main) {
                    callback(result)
                }
                jobList.remove(job)
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    callbackError(e)
                }
            }
        }
    }
}
package com.example.mymoxy.domain

import android.util.Log
import androidx.lifecycle.*
import com.example.mymoxy.tools.ResponseError
import com.sumin.shoppinglist.domain.DeleteShopItemUseCase
import com.sumin.shoppinglist.domain.EditShopItemUseCase
import com.sumin.shoppinglist.domain.GetShopListUseCase
import com.sumin.shoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class FirstFragmentViewModel(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val initDataUseCase: InitDataUseCase
) : ViewModel() {

    private val dispatcherWithError = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch(Dispatchers.Main) {
            mutableStateFlowError.emit(ResponseError.FailureUnknown(throwable.toString()))
            Log.d("FirstFragmentViewModel", throwable.toString())
        }
    } + CoroutineName("FirstFragmentViewModel") + Dispatchers.IO

    var sharedFlowShopitem: SharedFlow<List<ShopItem>> = getShopListUseCase.getShopList()

    private val mutableStateFlowError: MutableSharedFlow<ResponseError> = MutableSharedFlow()
    var stateFlowError: SharedFlow<ResponseError> = mutableStateFlowError.asSharedFlow()


    init {
        viewModelScope.launch(dispatcherWithError) {
            initDataUseCase.initData()
        }
    }

    fun deleteShopItem(item: ShopItem) {
        viewModelScope.launch(dispatcherWithError) {
            try {
                deleteShopItemUseCase.deleteShopItem(item)
            } catch (e: Exception) {
                mutableStateFlowError.emit(ResponseError.Failure(e.message))
            }
        }
    }

    fun editShopItem(item: ShopItem) {
        viewModelScope.launch(dispatcherWithError) {
            try {
                editShopItemUseCase.editShopItem(item)
            } catch (e: Exception) {
                mutableStateFlowError.emit(ResponseError.Failure(e.message))
            }
        }
    }

}

class FactoryFirstFragmentViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val initDataUseCase: InitDataUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FirstFragmentViewModel(
            getShopListUseCase,
            deleteShopItemUseCase,
            editShopItemUseCase,
            initDataUseCase
        ) as T
    }
}
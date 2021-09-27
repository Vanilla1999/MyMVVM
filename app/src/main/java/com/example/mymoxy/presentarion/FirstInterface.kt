package com.example.mymoxy.presentarion

import com.sumin.shoppinglist.domain.ShopItem
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FirstInterface: MvpView {
    @AddToEndSingle
    fun onInitImage()
    @AddToEndSingle
    fun onRecolorImage()
    @AddToEndSingle
    fun onInitShopList(stores: List<ShopItem>)
    @AddToEndSingle
    fun onChengeShopList(stores: List<ShopItem>)
}
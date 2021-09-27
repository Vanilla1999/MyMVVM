package com.example.mymoxy.presentarion

import androidx.recyclerview.widget.DiffUtil
import com.sumin.shoppinglist.domain.ShopItem

class DiffCallback(
    private val oldGalaxies: List<ShopItem>,
    private val newGalaxies: List<ShopItem>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldGalaxies.size
    }

    override fun getNewListSize(): Int {
        return newGalaxies.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // In the real world you need to compare something unique like id
        return oldGalaxies[oldItemPosition] == newGalaxies[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // This is called if areItemsTheSame() == true;
        return oldGalaxies[oldItemPosition] == newGalaxies[newItemPosition]
    }
}

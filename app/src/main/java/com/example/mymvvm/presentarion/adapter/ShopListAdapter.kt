package com.example.mymvvm.presentarion.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvm.R
import com.example.mymvvm.databinding.ItemShopDisabledBinding
import com.example.mymvvm.databinding.ItemShopEnabledBinding
import com.example.mymvvm.tools.then
import com.sumin.shoppinglist.domain.ShopItem
import java.lang.Exception
import java.lang.IllegalStateException

class ShopListAdapter(
    private val onStoreCallback: (ShopItem) -> Unit
) : ListAdapter<ShopItem, RecyclerView.ViewHolder>((object :
    DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }

    // важно
    override fun getChangePayload(oldItem: ShopItem, newItem: ShopItem): Any? {
        return super.getChangePayload(oldItem, newItem)
    }

})) {

    private val itemClick: (ShopItem) -> Unit = { shopItem: ShopItem -> onStoreCallback(shopItem) }


    //  @LayoutRes private val layoutRes: Int,
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.item_shop_enabled -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemShopEnabledBinding.inflate(inflater, parent, false)
                return ViewHolder(
                    binding, itemClick
                )
            }
            R.layout.item_shop_disabled -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemShopDisabledBinding.inflate(inflater, parent, false)
                return ViewHolderDisable(
                    binding, itemClick
                )
            }
            else -> throw IllegalStateException("Incorrect view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItem(position).enabled) {
            (holder as ViewHolder).bindView(getItem(position))
        } else {
            (holder as ViewHolderDisable).bindView(getItem(position))
        }
    }

    // чтоб использовать разные ViewHolder.
    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled)
            R.layout.item_shop_enabled else {
            R.layout.item_shop_disabled
        }
    }


    inner class ViewHolder(
        private val item: ItemShopEnabledBinding,
        private val itemClick: (ShopItem) -> Unit
    ) : RecyclerView.ViewHolder(item.root) {

        init {
            item.root.setOnClickListener {
                itemClick(getItem(bindingAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindView(model: ShopItem) {
            val status = model.enabled then "True" ?: "Flase"
            if (model.enabled) {
                item.tvName.text = "${model.name} $status"
                item.tvCount.text = model.count.toString()
                item.tvName.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.holo_blue_bright
                    )
                )
            }
        }
    }

    inner class ViewHolderDisable(
        private val item: ItemShopDisabledBinding,
        private val itemClick: (ShopItem) -> Unit
    ) : RecyclerView.ViewHolder(item.root) {

        init {
        }

        @SuppressLint("SetTextI18n")
        fun bindView(model: ShopItem) {
            val status = model.enabled then "True" ?: "Flase"
            item.root.setOnClickListener {
                itemClick(model)
            }
                item.tvName.text = "${model.name} $status"
                item.tvCount.text = model.count.toString()
                item.tvName.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.holo_red_light
                    )
                )
        }
    }


}
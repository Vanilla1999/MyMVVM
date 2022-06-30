package com.example.mymoxy.presentarion

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymoxy.R
import com.example.mymoxy.databinding.ItemShopDisabledBinding
import com.example.mymoxy.databinding.ItemShopEnabledBinding
import com.example.mymoxy.tools.then
import com.sumin.shoppinglist.domain.ShopItem
import java.lang.Exception
import java.lang.IllegalStateException

class ShopListAdapter(
    private val onStoreCallback: (ShopItem) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var shopListt = listOf<ShopItem>()
            set(value) {
                Log.d("TAG", "Pfxtybt sdfsdfsdf ")
                field = value
            }
    }
    private lateinit var clickListener: ClickListenerForAdapter
    private val itemClick:(Int)->Unit =  { position: Int -> onStoreCallback(shopListt[position]) }
    private var diffUtil: GenericItemDiff<ShopItem>? = null
    private val ENABLED = 1
    private val DISBALED = 2

    //  @LayoutRes private val layoutRes: Int,
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("TAG", "viewType= " + viewType)
        when(viewType){
            ENABLED -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemShopEnabledBinding.inflate(inflater, parent, false)
                return ViewHolder(
                    binding, itemClick
                )
            }
            DISBALED ->{
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemShopDisabledBinding.inflate(inflater, parent, false)
                return ViewHolderDisable(
                    binding, clickListener
                )
            }
            else -> throw IllegalStateException("Incorrect view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("TAG", "bind, position = " + position)
        if(shopListt[position].enabled) {
            (holder as ViewHolder).bindView(shopListt[position])
        } else{
            (holder as ViewHolderDisable).bindView(shopListt[position])
        }
    }

    // чтоб использовать разные ViewHolder.
    override fun getItemViewType(position: Int): Int {
        return if (shopListt[position].enabled)
            ENABLED else{
            DISBALED
        }
    }


    fun setItem(items: List<ShopItem>) {
        shopListt = items
    }

    fun setListener(item:ClickListenerForAdapter) {
        clickListener = item
    }

    fun update(items: List<ShopItem>) {
        Log.d("TAG", "Pfxtybt sdfsdfsdf ")
        val diffCallback = DiffCallback(shopListt, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback,true)
        try {
            shopListt = items
            diffResult.dispatchUpdatesTo(this)

        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
        }
    }

    inner class ViewHolder(
        private val item: ItemShopEnabledBinding,
        private val itemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(item.root) {

        init {
            item.root.setOnClickListener {
                itemClick(adapterPosition) }
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
        private val itemClick: ClickListenerForAdapter
    ) : RecyclerView.ViewHolder(item.root) {

        init {
        }

        @SuppressLint("SetTextI18n")
        fun bindView(model: ShopItem) {
            val status = model.enabled then "True" ?: "Flase"
            item.root.setOnClickListener {
                itemClick.onClickItem(model) }
            if (model.enabled) {
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

        fun flex(f: Int) {
            //TODO
        }
    }

    override fun getItemCount(): Int {
        return shopListt.size
    }


}

interface ClickListenerForAdapter{
    fun onClickItem(shop:ShopItem)
}
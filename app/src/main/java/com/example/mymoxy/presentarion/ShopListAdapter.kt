package com.example.mymoxy.presentarion

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymoxy.R
import com.example.mymoxy.databinding.ItemShopDisabledBinding
import com.example.mymoxy.databinding.ItemShopEnabledBinding
import com.example.mymoxy.tools.then
import com.sumin.shoppinglist.domain.ShopItem

class ShopListAdapter(private val onStoreCallback: (ShopItem) -> Unit):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val clickListener = { position: Int -> onStoreCallback(shopList[position]) }
    var shopList = listOf<ShopItem>()
    //  @LayoutRes private val layoutRes: Int,
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShopEnabledBinding.inflate(inflater, parent, false)
        return ViewHolder(
            binding, clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindView(shopList[position])
    }

    // чтоб использовать разные ViewHolder.
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setStores(list: List<ShopItem>) {
        shopList = list
        notifyDataSetChanged()
    }

    inner class  ViewHolder(private val item: ItemShopEnabledBinding, private val itemClick: (Int) -> Unit):RecyclerView.ViewHolder(item.root){

        init {
            item.root.setOnClickListener { itemClick(adapterPosition) }
        }
        @SuppressLint("SetTextI18n")
        fun bindView(model: ShopItem){
         val status = model.enabled then "True" ?: "Flase"
            itemView.setOnLongClickListener {
                true
            }
            if (model.enabled) {
                item.tvName.text = "${model.name} $status"
                item.tvCount.text = model.count.toString()
                item.tvName.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_red_light))
            }
        }
        fun flex(f:Int)
        {
            //TODO
        }
    }

    override fun getItemCount(): Int {
       return  shopList.size
    }

}


package com.example.mymvvm.presentarion.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvm.R

class CustomItemDecoration(private val dividerDrawable: Drawable): RecyclerView.ItemDecoration() {

   override fun getItemOffsets(
       outRect: Rect,
       view: View,
       parent: RecyclerView,
       state: RecyclerView.State
    ) {
       val currentPosition = parent.getChildAdapterPosition(view).takeIf { it != RecyclerView.NO_POSITION } ?: return
       val adapter = parent.adapter ?: return
       when(adapter.getItemViewType(currentPosition)){
           R.layout.item_shop_enabled ->{
              if(adapter.isNextTargetViewType(currentPosition,R.layout.item_shop_disabled))
                  outRect.bottom = dividerDrawable.intrinsicHeight else  outRect.bottom = 0
               if(adapter.isPrevTargetViewType(currentPosition,R.layout.item_shop_disabled)) {
                   outRect.top = dividerDrawable.intrinsicHeight
               } else{
                   outRect.top = 0
               }
           }
           R.layout.item_shop_disabled ->{
               outRect.bottom = 0
           }
       }
    }

    override  fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        parent.children
//            .forEachIndexed { i,_ ->
//                val view = parent.getChildAt(i)
//                val position = parent.getChildAdapterPosition(view)
//                    .let { if (it == RecyclerView.NO_POSITION) return else it }
//                if (position % 2 != 0) {
//                    val left = view.left
//                    val top = view.bottom
//                    val right = view.right
//                    val bottom = view.bottom+ dividerDrawable.intrinsicHeight - parent.paddingBottom
//                    dividerDrawable.bounds = Rect(left, top, right, bottom)
//                    dividerDrawable.draw(c)
//                }
//            }
    }

    private fun RecyclerView.Adapter<*>.isNextTargetViewType(
        currentPosition: Int,
        viewType: Int
    ): Boolean {
        val lastIndex = itemCount - 1
        return currentPosition != lastIndex && getItemViewType(currentPosition + 1) == viewType
    }

    private fun RecyclerView.Adapter<*>.isPrevTargetViewType(
        currentPosition: Int,
        viewType: Int
    ) = currentPosition != 0 && getItemViewType(currentPosition - 1) == viewType
}
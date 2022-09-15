package com.example.mymvvm.presentarion.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(private val dividerDrawable: Drawable): RecyclerView.ItemDecoration() {
    private var mDividerHeight = 0f

    private var mPaint = Paint()

    fun ColorDividerItemDecoration() {
        mPaint.setAntiAlias(true)
        mPaint.setColor(Color.RED)
    }

   override fun getItemOffsets(
       outRect: Rect,
       view: View,
       parent: RecyclerView,
       state: RecyclerView.State
    ) {
       val position = parent.getChildAdapterPosition(view)
           .let { if (it == RecyclerView.NO_POSITION) return else it }
       outRect.bottom =
           if (position % 2 == 0) 0
           else dividerDrawable.intrinsicWidth
    }

    override  fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children
            .forEachIndexed { i,_ ->
                val view = parent.getChildAt(i)
                val position = parent.getChildAdapterPosition(view)
                    .let { if (it == RecyclerView.NO_POSITION) return else it }
                if (position % 2 != 0) {
                    val left = view.left
                    val top = view.bottom
                    val right = view.right
                    val bottom = view.bottom+ dividerDrawable.intrinsicHeight - parent.paddingBottom
                    dividerDrawable.bounds = Rect(left, top, right, bottom)
                    dividerDrawable.draw(c)
                }
            }
    }
}
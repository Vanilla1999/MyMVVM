package com.example.mymvvm.presentarion.adapter

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration: RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
       // takeIf {  }
    }
}
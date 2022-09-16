package com.example.mymvvm.presentarion.adapter

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvm.R


class CustomItemDecoration(private val dividerDrawable: Drawable,resources:Resources) :
    RecyclerView.ItemDecoration() {

    @RequiresApi(Build.VERSION_CODES.M)
    private val paint: Paint = Paint().apply {
        color = resources.getColor(R.color.disable, null)
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        flags = Paint.ANTI_ALIAS_FLAG
    }
    private val rec: RectF = RectF()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val currentPosition =
            parent.getChildAdapterPosition(view).takeIf { it != RecyclerView.NO_POSITION } ?: return
        val adapter = parent.adapter ?: return
        when (adapter.getItemViewType(currentPosition)) {
            R.layout.item_shop_enabled -> {
                if (adapter.isNextTargetViewType(currentPosition, R.layout.item_shop_disabled))
                    outRect.bottom = dividerDrawable.intrinsicHeight else outRect.bottom = 0
                if (adapter.isPrevTargetViewType(currentPosition, R.layout.item_shop_disabled)) {
                    outRect.top = dividerDrawable.intrinsicHeight
                }
            }
            R.layout.item_shop_disabled -> {
                outRect.bottom = 0
                outRect.top = 0
            }
        }
    }

    private val path: Path = Path()
    private val paintGray: Paint = Paint().apply {
        color = Color.GREEN
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        flags = Paint.ANTI_ALIAS_FLAG
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children
            .forEachIndexed { i, _ ->
                val view = parent.getChildAt(i)
                val position = parent.getChildAdapterPosition(view)
                    .let { if (it == RecyclerView.NO_POSITION) return else it }
                val adapter = parent.adapter ?: return
                if (
                    adapter.isPrevTargetViewType(position,R.layout.item_shop_disabled) &&
                    adapter.isNextTargetViewType(position,R.layout.item_shop_enabled) &&
                    adapter.getItemViewType(position) == R.layout.item_shop_disabled
                ) {

// верхнее округление
//                    path.reset()
//                    path.moveTo(0f + parent.paddingLeft, view.top.toFloat())
//                    val top = view.top - (view.bottom - view.top).toFloat() / 2
//                    path.quadTo(
//                        parent.left.toFloat() ,
//                        top,
//                        0f + parent.paddingLeft + (view.right - view.left)*0.06f,
//                        top
//                    )
//                    path.lineTo((view.right-parent.paddingRight -(view.right - view.left) *0.06f).toFloat(),top)
//                    path.quadTo(
//                        parent.right.toFloat(),
//                        top,
//                        view.right-parent.paddingRight.toFloat(),
//                        view.top.toFloat()
//                    )
                        // нижнее округление
                    path.reset()
                    path.moveTo(0f + parent.paddingLeft, view.bottom.toFloat())
                    val bottom = view.bottom + (view.bottom - view.top).toFloat() / 4
                    path.quadTo(
                        parent.left.toFloat() ,
                        bottom,
                        0f + parent.paddingLeft + (view.right - view.left)*0.06f,
                        bottom
                    )
                    path.lineTo((view.right-parent.paddingRight -(view.right - view.left) *0.06f).toFloat(),bottom)
                    path.quadTo(
                        parent.right.toFloat(),
                        bottom,
                        view.right-parent.paddingRight.toFloat(),
                        view.bottom.toFloat()
                    )
                      c.drawPath(path, paint)
                }
            }
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
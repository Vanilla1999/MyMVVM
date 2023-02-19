package com.example.mymvvm.presentarion.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class CustomViewItemDecoration(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    // отвечает за измерение view сама себя измеряет, setMeasureDimention иначе краш
    // widthMeasureSpec
    // Exactly matchparrent или 200dp, atMost wrap_content , uspecified - какой угодно размер
    private val paint : Paint =Paint().apply {
        color = Color.GRAY
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        flags = Paint.ANTI_ALIAS_FLAG
    }
    private val paintGreen : Paint =Paint().apply {
        color = Color.GREEN
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        flags = Paint.ANTI_ALIAS_FLAG
    }
    private val path:Path = Path()
   private val rec: RectF = RectF()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        when (widthMode) {
            MeasureSpec.AT_MOST -> {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
            MeasureSpec.EXACTLY -> {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
            MeasureSpec.UNSPECIFIED -> {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
        }

    }

    // только для viewGroup для измреения потомков
    // в пикселях, где располагается элемент, можно расположить где захочешь
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        rec.set(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat()/2)
        super.onLayout(changed, left, top, right, bottom)
    }

    private fun Canvas.setPath() {

    }

    // рисуем
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(rec,paint)
        val corners = floatArrayOf(
            80f, 80f,   // Top left radius in px
            80f, 80f,   // Top right radius in px
            0f, 0f,     // Bottom right radius in px
            0f, 0f      // Bottom left radius in px
        )

        val path = Path()
        path.addRoundRect(rec, corners, Path.Direction.CW)
        canvas.drawPath(path, paintGreen)
    }



}
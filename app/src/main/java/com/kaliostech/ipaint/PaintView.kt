package com.kaliostech.ipaint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.kaliostech.ipaint.MainActivity.Companion.paintBrush
import com.kaliostech.ipaint.MainActivity.Companion.path

class PaintView : View {
    var params: ViewGroup.LayoutParams? = null

    companion object {
        var pathlist = ArrayList<Path>()
        var colorlist = ArrayList<Int>()
        var strokelist = ArrayList<Float>()
        var currentBrush = Color.BLACK
        var currentStroke = 12f
    }

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paintBrush.isAntiAlias = true
        paintBrush.color = currentBrush
        paintBrush.strokeWidth = currentStroke
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path = Path() // Create a new Path instance
                path.moveTo(x, y)
                pathlist.add(path)
                colorlist.add(currentBrush)
                strokelist.add(currentStroke)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {
                // No-op
            }
            else -> return false
        }
        postInvalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        for (i in pathlist.indices) {
            paintBrush.color = colorlist[i]
            paintBrush.strokeWidth = strokelist[i]
            canvas.drawPath(pathlist[i], paintBrush)
        }
        invalidate()
    }
}

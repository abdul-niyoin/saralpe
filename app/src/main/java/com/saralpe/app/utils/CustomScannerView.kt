package com.saralpe.app.utils

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.core.ViewFinderView
import me.dm7.barcodescanner.zxing.ZXingScannerView

class CustomScannerView : ZXingScannerView {
    constructor(context: Context?) : super(context) {
        Log.d("TAG19", "CustomScannerView")
    }

    constructor(context: Context?, attributeSet: AttributeSet?) : super(context, attributeSet) {}

    override fun createViewFinderView(context: Context): IViewFinder {
        return CustomViewFinderView(context)
    }

    class CustomViewFinderView(context: Context) : ViewFinderView(context) {
        private var cntr = 0
        private var goingup = false

        init {
            Log.d("TAG19", "CustomViewFinderView")
            setSquareViewFinder(true)
            val displayMetrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
            // DEFAULT SQUARE DIMENSION RATIO in ViewFinderView is 0.625
            // get appropriate Dimension ratio otherwise
            val width = displayMetrics.widthPixels * 0.625f
            setBorderLineLength(width.toInt())
        }

        override fun drawLaser(canvas: Canvas) {
            Log.d("TAG19", "DRAW LASER")
            val mFramingRect = framingRect
            // Draw a red "laser scanner" line through the middle to show decoding is active
            mLaserPaint.alpha =
                SCANNER_ALPHA[scannerAlpha]
            scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.size
            var middle = mFramingRect.height() / 2 + mFramingRect.top
            middle = middle + cntr
            if (cntr < 210 && !goingup) {
                canvas.drawRect(
                    (mFramingRect.left + 2).toFloat(),
                    (middle - 1).toFloat(),
                    (mFramingRect.right - 1).toFloat(),
                    (middle + 2).toFloat(),
                    mLaserPaint
                )
                cntr = cntr + 5
            }
            if (cntr >= 210 && !goingup) {
                goingup = true
            }
            if (cntr > -210 && goingup) {
                canvas.drawRect(
                    (mFramingRect.left + 2).toFloat(),
                    (middle - 1).toFloat(),
                    (mFramingRect.right - 1).toFloat(),
                    (middle + 2).toFloat(),
                    mLaserPaint
                )
                cntr = cntr - 5
            }
            if (cntr <= -210 && goingup) {
                goingup = false
            }
            postInvalidateDelayed(
                ANIMATION_DELAY,
                mFramingRect.left - POINT_SIZE,
                mFramingRect.top - POINT_SIZE,
                mFramingRect.right + POINT_SIZE,
                mFramingRect.bottom + POINT_SIZE
            )
        }
    }

    companion object {
        private val SCANNER_ALPHA = intArrayOf(0, 64, 128, 192, 255, 192, 128, 64)
        private var scannerAlpha = 0
        private const val POINT_SIZE = 20
        private const val ANIMATION_DELAY = 5L
    }
}
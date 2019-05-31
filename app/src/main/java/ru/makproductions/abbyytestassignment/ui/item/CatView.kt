package ru.makproductions.abbyytestassignment.ui.item

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CatView : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    val imageView = ImageView(context)
    val textView = TextView(context)
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        orientation = HORIZONTAL
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.weight = 1.toFloat()
        initImageView(layoutParams)
        initTextView(layoutParams)
        addViews()
    }

    private fun addViews() {
        this.addView(imageView)
        this.addView(textView)
    }

    private fun initImageView(layoutParams: LayoutParams) {
        imageView.layoutParams = layoutParams
        imageView.setPadding(5, 5, 5, 5)
        imageView.minimumHeight = convertDpsToPixels(200)
    }

    private fun initTextView(layoutParams: LayoutParams) {
        textView.layoutParams = layoutParams
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.setPadding(5, 5, 5, 5)
    }

    fun convertDpsToPixels(dps: Int): Int {
        val scale = context.resources.displayMetrics.density
        val pixels = (dps.toFloat() * scale + 0.5f).toInt()
        return pixels
    }
}
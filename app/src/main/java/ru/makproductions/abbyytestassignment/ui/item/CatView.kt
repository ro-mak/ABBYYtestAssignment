package ru.makproductions.abbyytestassignment.ui.item

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.cat_view.view.*
import ru.makproductions.abbyytestassignment.R

class CatView : LinearLayout {
    lateinit var catImageView: ImageView
    lateinit var catTextView: TextView

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.cat_view, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        this.catImageView = cat_image_view
        this.catTextView = cat_text_view
        orientation = HORIZONTAL
    }
}
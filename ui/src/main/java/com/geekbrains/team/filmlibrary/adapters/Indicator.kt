package com.geekbrains.team.filmlibrary.adapters

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.model.MovieView

class Indicator(
    val context : Context?,
    private val indicator: LinearLayout,
    private val indicatorItem: LinearLayout,
    val adapter: OneItemAdapter<MovieView>
) {

    fun startIndicators() {
        indicator.removeAllViews()
        val indicators = arrayOfNulls<ImageView>(adapter.itemCount)

        for (i in indicators.indices) {
            indicators[i] = ImageView(context)
            indicators[i]?.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.indicator_inactive
                    )
                }
            )
            indicators[i]?.layoutParams = indicatorItem.layoutParams
            indicator.addView(indicators[i])
        }
    }

    fun setCurrentIndicator(index: Int) {
        val childCount: Int = indicator.childCount
        for (i in 0 until childCount) {
            val imageView =
                indicator.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.indicator_active
                        )
                    }
                )
            } else {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.indicator_inactive
                        )
                    }
                )
            }
        }
    }
}
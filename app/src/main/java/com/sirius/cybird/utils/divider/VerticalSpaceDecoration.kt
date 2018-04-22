package com.sirius.cybird.utils.divider

import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

class VerticalSpaceDecoration(@DimenRes private val startSpace: Int,
                              @DimenRes private val endSpace: Int,
                              @DimenRes private val itemSpace: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = parent.context.resources.getDimensionPixelSize(itemSpace)
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = parent.context.resources.getDimensionPixelSize(startSpace)
        }
//        if (parent.getChildLayoutPosition(view) == state.itemCount - 1) {
//            outRect.right = parent.context.resources.getDimensionPixelSize(endSpace)
//        }
    }
}
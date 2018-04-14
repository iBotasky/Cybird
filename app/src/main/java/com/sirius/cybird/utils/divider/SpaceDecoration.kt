package com.sirius.cybird.utils.divider

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpaceDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.right = space
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = space
        }
    }
}
package com.sirius.cybird.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Create by Botasky 2018/11/16
 */
public class VerticalRecyclerView extends RecyclerView {
    private int mLastXIntercept;
    private int mLastYIntercept;


    public VerticalRecyclerView(Context context) {
        this(context, null);
    }

    public VerticalRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        Log.e("TAG", "OnIntercept");
        int x = ((int) e.getX());
        int y = ((int) e.getY());
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastYIntercept = y;
                mLastXIntercept = x;
                return false;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                //水平移动距离》垂直方向移动距离不拦截
                return  Math.abs(deltaX) < Math.abs(deltaY);
        }
        return super.onInterceptTouchEvent(e);
    }
}

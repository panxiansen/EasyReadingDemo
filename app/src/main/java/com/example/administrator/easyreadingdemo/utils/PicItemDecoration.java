package com.example.administrator.easyreadingdemo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/7/6.
 */

public class PicItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private int mOrientation ;

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;


    public PicItemDecoration(Context context){
        final TypedArray array = context.obtainStyledAttributes(ATTRS);
        mDivider = array.getDrawable(0);
        array.recycle();

    }



    //水平方向分割线
    public void drawHorizontal(Canvas c, RecyclerView parent){
        int childCount = parent.getChildCount();
        for (int i = 0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //垂直方向分割线
    public void drawVertical(Canvas c, RecyclerView parent){
        final int childCount = parent.getChildCount();
        for (int i = 0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        drawHorizontal(c,parent);
        drawVertical(c,parent);

    }
}

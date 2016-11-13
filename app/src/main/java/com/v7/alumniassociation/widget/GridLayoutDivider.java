package com.v7.alumniassociation.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by v7 on 2016/11/10.
 */

public class GridLayoutDivider extends RecyclerView.ItemDecoration {
    int color;
    int width;

    public GridLayoutDivider(int color, int width) {
        this.color = color;
        this.width = width;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(width,width,outRect.right+width,outRect.bottom+width);
    }


}

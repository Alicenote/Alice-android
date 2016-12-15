package com.namestore.alicenote.common.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kienht on 12/5/16.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public SpacesItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.top = mSpace * 2;

        //first Child
        if ((parent.getChildCount() > 0 && parent.getChildPosition(view) == 0)) {
            outRect.left = mSpace * 8;
        }

        //end Child
        if ((parent.getChildCount() > 2 && parent.getChildPosition(view) == 2)) {
            outRect.right = mSpace * 8;
        }
    }

}

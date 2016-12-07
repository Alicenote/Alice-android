package com.namestore.alicenote.common.recycler;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.namestore.alicenote.R;

/**
 * Created by kienht on 12/7/16.
 */
public class SimpleDividerItemDecoration extends DividerItemDecoration {


    private final int mSpace;

    public SimpleDividerItemDecoration(Context context, int orientation, int mSpace) {
        super(context, orientation);
        setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider));
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mSpace;
        outRect.bottom = mSpace;
    }
}

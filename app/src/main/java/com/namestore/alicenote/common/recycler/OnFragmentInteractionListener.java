package com.namestore.alicenote.common.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public interface OnFragmentInteractionListener {
    void onViewClick(String tag);

    void onViewClick(String tag, Object object);


}
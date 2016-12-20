package com.namestore.alicenote.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.namestore.alicenote.common.AppUtils;

/**
 * Created by kienht on 12/16/16.
 */

public class BrandonBoldTextView extends TextView {
    public BrandonBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public BrandonBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BrandonBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BrandonBoldTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), AppUtils.BOLD);
        setTypeface(typeface);
    }
}

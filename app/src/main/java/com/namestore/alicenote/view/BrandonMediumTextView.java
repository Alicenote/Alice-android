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

public class BrandonMediumTextView extends TextView {
    public BrandonMediumTextView(Context context) {
        super(context);
        init(context);
    }

    public BrandonMediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BrandonMediumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BrandonMediumTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), AppUtils.MEDIUM);
        setTypeface(typeface);
    }
}

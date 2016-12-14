package com.namestore.alicenote.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by kienht on 11/8/16.
 */

public class ViewUtils {

    public static void configEditText(final Activity activity, final EditText editText,
                                      final LinearLayout linearLayout, final String hint, int icon, final TextView textView) {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setHint("");
                editText.requestFocusFromTouch();
                editText.setFocusableInTouchMode(true);
                if (textView != null) {
                    textView.setVisibility(View.INVISIBLE);
                }

                InputMethodManager keyBoard =
                        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyBoard.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editText.clearFocus();
                    editText.setHint(hint);
                    editText.setFocusableInTouchMode(false);
                }
            }
        });

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linearLayout.setFocusable(true);
                int childCount = linearLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    view = linearLayout.getChildAt(i);
                    view.clearFocus();
                }
                InputMethodManager keyBoard =
                        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyBoard.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);

                return false;
            }

        });
        editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
    }


    public static void configSpinner(Activity activity, ArrayList<String> arrayList, Spinner... spinners) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (Spinner spinner : spinners) {
            spinner.setAdapter(adapter);
            spinner.setSelection(0);
        }

    }

    public static void setAndScaleDrawableButton(Button btn, int left, int top, int right, int bottom, double fitFactor) {
        btn.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        Drawable[] drawables = btn.getCompoundDrawables();
        for (int i = 0; i < drawables.length; i++) {
            if (drawables[i] != null) {
                if (drawables[i] instanceof ScaleDrawable) {
                    drawables[i].setLevel(1);
                }
                drawables[i].setBounds(0, 0, (int) (drawables[i].getIntrinsicWidth() * fitFactor),
                        (int) (drawables[i].getIntrinsicHeight() * fitFactor));
                ScaleDrawable sd = new ScaleDrawable(drawables[i], 0, drawables[i].getIntrinsicWidth(), drawables[i].getIntrinsicHeight());
                if (i == 0) {
                    btn.setCompoundDrawables(sd.getDrawable(), drawables[1], drawables[2], drawables[3]);
                } else if (i == 1) {
                    btn.setCompoundDrawables(drawables[0], sd.getDrawable(), drawables[2], drawables[3]);
                } else if (i == 2) {
                    btn.setCompoundDrawables(drawables[0], drawables[1], sd.getDrawable(), drawables[3]);
                } else {
                    btn.setCompoundDrawables(drawables[0], drawables[1], drawables[2], sd.getDrawable());
                }
            }
        }
    }

    //remove animation ShiftMode of BottomNavigationView
    public static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            AppUtils.logE("ERROR NO SUCH FIELD Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            AppUtils.logE("ERROR ILLEGAL ALG Unable to change value of shift mode");
        }
    }

}

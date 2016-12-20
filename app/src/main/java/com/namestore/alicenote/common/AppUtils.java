package com.namestore.alicenote.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.common.dialog.DialogNotice;

import java.util.regex.Pattern;

/**
 * Created by kienht on 11/8/16.
 */

public class AppUtils {

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static final Pattern FISRT_LAST_NAME_PATTERN = Pattern.compile(
            "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]{3,10}+$"
    );

    public static String MEDIUM = "fonts/brandon_med.ttf";
    public static String REGULAR = "fonts/brandon_reg.ttf";
    public static String BOLD = "fonts/brandon_bold.ttf";
    public static String BLACK = "fonts/brandon_black.ttf";

    public static Typeface getTypeFont(Activity activity, String string) {
        return Typeface.createFromAsset(activity.getAssets(), string);
    }

    public static void setTypeFontForTextView(Activity activity, String tag, TextView... textViews) {
        Typeface type = Typeface.createFromAsset(activity.getAssets(), tag);
        for (TextView textview : textViews) {
            textview.setTypeface(type);
        }
    }

    public static void setTypeFontForButton(Activity activity, String tag, Button... buttons) {
        Typeface type = Typeface.createFromAsset(activity.getAssets(), tag);
        for (Button button : buttons) {
            button.setTypeface(type);
        }
    }

    public static void setTypeFontForEditText(Activity activity, String tag, EditText... editTexts) {
        Typeface type = Typeface.createFromAsset(activity.getAssets(), tag);
        for (EditText editText : editTexts) {
            editText.setTypeface(type);
        }
    }

    public static void hideKeyBoard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static int convertDpToPx(Activity activity, float dp) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, activity.getResources().getDisplayMetrics());
        return Math.round(pixels);
    }

    public static void logE(String mess) {
        Log.e(Constants.TAG, mess);
    }

    public static void showShortToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static boolean checkFirstLastName(String name) {
        return FISRT_LAST_NAME_PATTERN.matcher(name).matches();
    }

    public static void showNoticeDialog(Activity activity, String string) {
        DialogNotice dialogNotice = new DialogNotice();
        dialogNotice.showDialog(activity, string);
    }

    public static int getWidthScreen(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        return width;
    }

    public static int getHeightScreen(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        return height;
    }

}

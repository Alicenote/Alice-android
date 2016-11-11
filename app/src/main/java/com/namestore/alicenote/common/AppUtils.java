package com.namestore.alicenote.common;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
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

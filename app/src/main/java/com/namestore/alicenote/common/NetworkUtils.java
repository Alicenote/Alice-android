package com.namestore.alicenote.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    public static boolean hasNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null)
            return false;
        if (!ni.isConnected())
            return false;
        if (!ni.isAvailable())
            return false;
        return true;
    }

}

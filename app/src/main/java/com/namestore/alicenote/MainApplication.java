package com.namestore.alicenote;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.activeandroid.app.Application;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

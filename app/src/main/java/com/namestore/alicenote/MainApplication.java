package com.namestore.alicenote;

import android.content.Context;
import android.support.multidex.MultiDex;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.app.Application;
import com.namestore.alicenote.common.activeandroid.AaConfig;
import com.namestore.alicenote.models.SampleTableObj;

@SuppressWarnings("ALL")
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Active Android
        Configuration.Builder builder =
                AaConfig.getDefaultConfig(getApplicationContext(), Constants.Database.DB_NAME,
                        Constants.Database.DB_VER);

        builder.addModelClasses(SampleTableObj.class);
        ActiveAndroid.initialize(builder.create());
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

package com.namestore.alicenote.common.activeandroid;

import android.content.Context;
import com.activeandroid.Configuration;

public class AaConfig {

    private AaConfig() {
        // not allow init an instance.
    }

    public static Configuration.Builder getDefaultConfig(Context ctx, String dbName, int dbVer) {
        Configuration.Builder confBuilder =
                new Configuration.Builder(ctx.getApplicationContext()).setDatabaseName(dbName)
                        .setDatabaseVersion(dbVer);
        return confBuilder;
    }
}
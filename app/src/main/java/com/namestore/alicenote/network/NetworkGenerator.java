package com.namestore.alicenote.network;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.namestore.alicenote.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kienht on 11/4/16.
 */

public class NetworkGenerator {

    AliceApi api;

    public AliceApi getApi() {
        return api;
    }

    static NetworkGenerator instance;

    public static NetworkGenerator getInstance() {
        clearInstance();
        if (instance == null) {
            instance = new NetworkGenerator();
        }
        return instance;
    }

    private NetworkGenerator() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient defaultHttpClient =
                new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request request = null;
                                if (!TextUtils.isEmpty(Constants.TOKEN)) {
                                    request = chain.request()
                                            .newBuilder()
                                            .addHeader("Content-Type", "application/json")
                                            .addHeader("token", Constants.TOKEN)
                                            .build();
                                }
                                return chain.proceed(request);
                            }
                        })
                        .addInterceptor(logging)
                        .build();

        Retrofit _retrofit = new Retrofit.Builder().baseUrl(Constants.URL_SERVER)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(defaultHttpClient)
                .build();

        api = _retrofit.create(AliceApi.class);
    }

    public static void clearInstance() {
        instance = null;
    }


}

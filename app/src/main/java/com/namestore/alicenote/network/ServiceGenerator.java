package com.namestore.alicenote.network;

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
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kienht on 11/4/16.
 */

public class ServiceGenerator {

    public static <S> S creatService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
//                        Request request = chain.request()
//                                .newBuilder()
//                                .addHeader("Content-Type", "application/json")
//                                .addHeader("Accept", "application/json")
//                                .addHeader("token", "JvM5QOH7E2acM1PpIyazWjSSPVzA44Cj")
//                                .build();
//                        return chain.proceed(request);

                        Request original = chain.request();

                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")

                                .addHeader("token", "JvM5QOH7E2acM1PpIyazWjSSPVzA44Cj")
                                .method(original.method(), original.body());

                        Request request = requestBuilder.build();

                        return chain.proceed(request);

                    }
                })
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit.create(serviceClass);
    }

}
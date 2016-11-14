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
 * Created by nhocnhinho on 12/11/2016.
 */

public class ServiceDashBoardGenerator {
    public static <S> S creatService(Class<S> serviceClass/* ,final Authorization auth*/) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {

                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Accept", "application/json")

                                .header("token","JvM5QOH7E2acM1PpIyazWjSSPVzA44Cj")
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

package com.geekbrains.poplibs4.dependences;

import com.geekbrains.poplibs4.rest.RestAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ActivityModule.class)
class RetrofitModule {

    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    RestAPI getRestApi(Retrofit retrofit) {
        return retrofit.create(RestAPI.class);
    }
}

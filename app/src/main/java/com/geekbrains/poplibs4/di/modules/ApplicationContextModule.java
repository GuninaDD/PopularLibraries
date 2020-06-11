package com.geekbrains.poplibs4.di.modules;

import android.content.Context;

import com.geekbrains.poplibs4.di.qualifiers.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModule {
    private Context context;

    public ApplicationContextModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @Provides
    Context getAppContext() {
        return context.getApplicationContext();
    }
}

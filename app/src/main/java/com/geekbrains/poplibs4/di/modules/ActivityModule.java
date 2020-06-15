package com.geekbrains.poplibs4.di.modules;

import android.app.Activity;
import android.content.Context;


import com.geekbrains.poplibs4.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Context context;
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.context = activity;
        this.activity = activity;
    }

    @Provides
    public Context getActivityContext() {
        return this.context;
    }

    @Provides
    public MainActivity getMainActivity() {
        return (MainActivity) this.activity;
    }

    @Provides
    public Activity getActivity() {
        return this.activity;
    }
}
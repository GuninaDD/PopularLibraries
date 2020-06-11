package com.geekbrains.poplibs4.dependences;

import android.content.Context;

import com.geekbrains.poplibs4.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Context context;
    private MainActivity activity;

    public ActivityModule(MainActivity activity) {
        this.context = activity;
        this.activity = activity;
    }

    @Provides
    Context getActivityContext() {
        return this.context;
    }

    @Provides
    MainActivity getActivity() {
        return this.activity;
    }
}
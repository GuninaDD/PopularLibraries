package com.geekbrains.poplibs4.di;


import com.geekbrains.poplibs4.data.MainDataHelper;
import com.geekbrains.poplibs4.data.UsersDataHelper;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {

    @Provides
    @Singleton
    public MainDataHelper getModel() {
        return Mockito.mock(MainDataHelper.class);
    }

    @Provides
    @Singleton
    public UsersDataHelper getUserHelper() {
        return Mockito.mock(UsersDataHelper.class);
    }
}
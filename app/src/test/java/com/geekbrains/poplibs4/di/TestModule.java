package com.geekbrains.poplibs4.di;

import com.geekbrains.poplibs4.data.DataWorker;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {

    @Provides
    @Singleton
    public DataWorker getModel() {
        return Mockito.mock(DataWorker.class);
    }
}
package com.geekbrains.poplibs4.di;

import com.geekbrains.poplibs4.DataInstrumentedTest;
import com.geekbrains.poplibs4.data.DataWorker;
import com.geekbrains.poplibs4.di.modules.ActivityModule;
import com.geekbrains.poplibs4.di.modules.DataModule;
import com.geekbrains.poplibs4.di.modules.NetworkModule;
import com.geekbrains.poplibs4.rest.RestAPI;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {NetworkModule.class, DataModule.class, ActivityModule.class})
@Singleton
public interface TestInstrumentalComponent {
    void inject(DataInstrumentedTest test);
    void inject(DataWorker dataWorker);

    RestAPI getApi();
}
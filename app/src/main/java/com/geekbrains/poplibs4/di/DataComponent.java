package com.geekbrains.poplibs4.di;

import com.geekbrains.poplibs4.MainPresenter;
import com.geekbrains.poplibs4.di.modules.ActivityModule;
import com.geekbrains.poplibs4.di.modules.DataModule;
import com.geekbrains.poplibs4.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, DataModule.class, ActivityModule.class})
public interface DataComponent {

    void injectToPresenter(MainPresenter presenter);

    boolean isConnected();
}
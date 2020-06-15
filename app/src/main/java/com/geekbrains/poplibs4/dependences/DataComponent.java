package com.geekbrains.poplibs4.dependences;

import com.geekbrains.poplibs4.presenters.MainPresenter;
import com.geekbrains.poplibs4.di.modules.DataModule;
import com.geekbrains.poplibs4.di.modules.NetworkModule;
import com.geekbrains.poplibs4.di.modules.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, DataModule.class, ActivityModule.class})
public interface DataComponent {

    void injectToPresenter(MainPresenter presenter);

    boolean isConnected();
}
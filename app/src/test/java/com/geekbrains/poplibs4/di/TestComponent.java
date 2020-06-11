package com.geekbrains.poplibs4.di;

import com.geekbrains.poplibs4.MainPresenter;
import com.geekbrains.poplibs4.MainPresenterTest;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {TestModule.class})
@Singleton
public interface TestComponent {
    void inject(MainPresenter presenter);

    void inject(MainPresenterTest mainPresenterTest);
}
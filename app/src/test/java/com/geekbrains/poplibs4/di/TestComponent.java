package com.geekbrains.poplibs4.di;

import com.geekbrains.poplibs4.UserPresenterTest;
import com.geekbrains.poplibs4.presenters.MainPresenter;
import com.geekbrains.poplibs4.MainPresenterTest;
import com.geekbrains.poplibs4.presenters.UsersPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {TestModule.class})
@Singleton
public interface TestComponent {
    void inject(MainPresenter presenter);

    void inject(MainPresenterTest mainPresenterTest);

    void inject(UsersPresenter presenter);
    void inject(UserPresenterTest userPresenterTest);
}
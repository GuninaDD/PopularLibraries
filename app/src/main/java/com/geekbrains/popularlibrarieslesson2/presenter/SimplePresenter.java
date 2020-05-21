package com.geekbrains.popularlibrarieslesson2.presenter;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SimplePresenter extends BasePresenter {
    private String lastValue = "Ничего не введено";

    public Disposable bindView(Observable<String> src, Consumer<String> dst){
        PublishSubject<String> bus = PublishSubject.create();
        Disposable disposable = bus.subscribe(dst);
        bus.onNext(lastValue);
        disposable.dispose();
        return src.map(value->lastValue = value).subscribe(dst);
    }

    public void unbindView(Disposable disposable){
        if(!disposable.isDisposed()) disposable.dispose();
    }
}

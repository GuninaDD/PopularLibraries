package com.geekbrains.popularlibrarieslesson2;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.geekbrains.popularlibrarieslesson2.presenter.PresenterManager;
import com.geekbrains.popularlibrarieslesson2.presenter.SimplePresenter;
import com.geekbrains.popularlibrarieslesson2.watcher.SimpleWatcher;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends Activity {
    private SimplePresenter presenter;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            presenter = new SimplePresenter();
        } else {
            presenter = (SimplePresenter) PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Observable<String> observable = Observable.create(emitter -> ((TextInputEditText) findViewById(R.id.editText))
                .addTextChangedListener((SimpleWatcher) (editable -> emitter.onNext(editable.toString()))));
        disposable = presenter.bindView(observable,value->((TextView)findViewById(R.id.textView)).setText(value));
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView(disposable);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

}

package com.geekbrains.poplibs4;

import com.geekbrains.poplibs4.data.DataWorker;
import com.geekbrains.poplibs4.data.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainPresenter {
    private MainActivity view;
    @Inject
    DataWorker model;
    private CompositeDisposable bag = new CompositeDisposable();

    private DisposableSingleObserver<List<UserEntity>> getDataObserver() {
        return new DisposableSingleObserver<List<UserEntity>>() {
            @Override
            public void onSuccess(List<UserEntity> data) {
                onProcessFinished(data);
            }

            @Override
            public void onError(Throwable e) {
                onFailure(e.getLocalizedMessage());
                e.printStackTrace();
            }
        };
    }

    private void onProcessFinished(List<UserEntity> data) {
        view.updateRecyclerData(data);
        view.updateRecyclerView();
        view.stopProgress();
    }

    void loadNetData(String request) {
        view.startProgress();
        if (request.isEmpty()) {
            loadAllUsers();
        } else {
            loadSingleUser(request);
        }
    }

    private void loadAllUsers() {
        model.getAllUsers()
                .subscribe(getDataObserver());
    }

    private void loadSingleUser(String name) {
        model.loadUser(name)
                .subscribe(new DisposableSingleObserver<UserEntity>() {
                    @Override
                    public void onSuccess(UserEntity userEntity) {
                        List<UserEntity> singleList = new ArrayList<>(1);
                        singleList.add(userEntity);
                        onProcessFinished(singleList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onFailure(e.getLocalizedMessage());
                        e.printStackTrace();
                    }
                });
    }

    void onRoomSaveClicked(List<UserEntity> data) {
        view.startProgress();
        model.testRoomSaveData(data);
    }

    void onRealmSaveClicked(List<UserEntity> data) {
        view.startProgress();
        model.testRealmSaveData(data);
    }

    void onRoomLoadClicked() {
        view.startProgress();
        model.testRoomLoadData()
                .subscribe(getDataObserver());
    }

    void onRealmLoadClicked() {
        view.startProgress();
        model.testRealmLoadData()
                .subscribe(getDataObserver());
    }

    private void onFailure(String error) {
        view.stopProgress();
        view.showError(error);
    }

    void bindView(MainActivity view) {
        this.view = view;
        Disposable d = model.subscribeOnResults()
                .subscribe(
                        result -> {
                            view.setResult(result);
                            view.updateResult();
                            view.stopProgress();
                        },
                        e -> {
                            e.printStackTrace();
                            onFailure(e.getLocalizedMessage());
                        }
                );
        bag.add(d);
    }

    void unbindView() {
        this.view = null;
        bag.clear();
    }
}
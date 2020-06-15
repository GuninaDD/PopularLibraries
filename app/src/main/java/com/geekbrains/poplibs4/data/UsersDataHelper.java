package com.geekbrains.poplibs4.data;

import com.geekbrains.poplibs4.data.entities.RepoEntity;
import com.geekbrains.poplibs4.rest.RestAPI;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UsersDataHelper {
    private RestAPI api;

    public UsersDataHelper(RestAPI api) {
        this.api = api;
    }

    public Single<List<RepoEntity>> getRepos(String userName) {
        return api.getUserRepos(userName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
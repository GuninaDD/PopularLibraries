package com.geekbrains.poplibs4.rest;

import com.geekbrains.poplibs4.data.entities.RepoEntity;
import com.geekbrains.poplibs4.data.entities.UserEntity;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestAPI {
    @GET("users/{path}")
    Single<UserEntity> getUser(@Path("path") String user);

    @GET("users")
    Single<List<UserEntity>> getAllUsers();

    @GET("users/{path}/repos")
    Single<List<RepoEntity>> getUserRepos(@Path("path") String user);
}
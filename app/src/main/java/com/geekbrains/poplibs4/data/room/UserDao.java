package com.geekbrains.poplibs4.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.geekbrains.poplibs4.data.entities.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAllUsers();

    @Insert
    void saveAll(List<UserEntity> data);

    @Query("DELETE FROM UserEntity")
    void deleteAll();
}
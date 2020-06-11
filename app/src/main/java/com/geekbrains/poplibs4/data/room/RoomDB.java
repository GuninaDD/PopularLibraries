package com.geekbrains.poplibs4.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geekbrains.poplibs4.data.entities.UserEntity;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    public abstract UserDao getUsersDao();
}
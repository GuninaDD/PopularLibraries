package com.geekbrains.poplibs4.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.geekbrains.poplibs4.data.MainDataHelper;
import com.geekbrains.poplibs4.data.UsersDataHelper;
import com.geekbrains.poplibs4.data.room.RoomDB;
import com.geekbrains.poplibs4.di.qualifiers.ApplicationContext;
import com.geekbrains.poplibs4.rest.RestAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationContextModule.class)
public class DataModule {
    private static final String DB_NAME = "RoomDB";

    @Provides
    public MainDataHelper getDataHelper(RoomDB db, RestAPI api) {
        return new MainDataHelper(db, api);
    }

    @Provides
    public UsersDataHelper getUserDataHelper(RestAPI api) {
        return new UsersDataHelper(api);
    }

    @Provides
    @Singleton
    RoomDB getRoomDB(@ApplicationContext Context applicationContext) {
        return Room.databaseBuilder(
                applicationContext.getApplicationContext(),
                RoomDB.class,
                DB_NAME
        ).build();
    }
}

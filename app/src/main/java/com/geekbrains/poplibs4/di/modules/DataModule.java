package com.geekbrains.poplibs4.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.geekbrains.poplibs4.data.DataWorker;
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
    DataWorker getDataHelper(RoomDB db, RestAPI api) {
        return new DataWorker(db, api);
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

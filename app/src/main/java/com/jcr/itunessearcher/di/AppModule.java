/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jcr.itunessearcher.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.jcr.itunessearcher.data.database.DaoiTunes;
import com.jcr.itunessearcher.data.database.DatabaseiTunes;
import com.jcr.itunessearcher.data.network.ServiceiTunes;
import com.jcr.itunessearcher.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton @Provides
    ServiceiTunes provideiTunesService() {
        return new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(ServiceiTunes.class);
    }

    @Singleton @Provides
    DatabaseiTunes provideDb(Application app) {
        return Room.databaseBuilder(app, DatabaseiTunes.class,"iTunes.db").build();
    }

    @Singleton @Provides
    DaoiTunes provideiTunesDao(DatabaseiTunes db) {
        return db.iTunesDao();
    }
}

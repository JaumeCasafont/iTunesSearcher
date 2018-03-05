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

package com.jcr.itunessearcher.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jcr.itunessearcher.AppExecutors;
import com.jcr.itunessearcher.data.database.DaoiTunes;
import com.jcr.itunessearcher.data.database.DatabaseiTunes;
import com.jcr.itunessearcher.data.models.Resource;
import com.jcr.itunessearcher.data.models.ResultiTune;
import com.jcr.itunessearcher.data.models.ResultiTunesSearch;
import com.jcr.itunessearcher.data.network.ApiResponse;
import com.jcr.itunessearcher.data.network.ResponseiTunesSearch;
import com.jcr.itunessearcher.data.network.ServiceiTunes;
import com.jcr.itunessearcher.util.AbsentLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Repository that handles ResultiTunes instances.
 */
@Singleton
public class RepositoryiTunes {

    private final DatabaseiTunes db;

    private final DaoiTunes iTunesDao;

    private final ServiceiTunes iTunesService;

    private final AppExecutors appExecutors;

    @Inject
    public RepositoryiTunes(AppExecutors appExecutors, DatabaseiTunes db, DaoiTunes daoiTunes,
                            ServiceiTunes iTunesService) {
        this.db = db;
        this.iTunesDao = daoiTunes;
        this.iTunesService = iTunesService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<ResultiTune>>> search(String query) {
        return new NetworkBoundResource<List<ResultiTune>, ResponseiTunesSearch>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull ResponseiTunesSearch item) {
                List<Integer> repoIds = item.getRepoIds();
                ResultiTunesSearch repoSearchResult = new ResultiTunesSearch(
                        query, repoIds, item.getResultCount());
                db.beginTransaction();
                try {
                    iTunesDao.insertRepos(item.getResults());
                    iTunesDao.insert(repoSearchResult);
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<ResultiTune> data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<List<ResultiTune>> loadFromDb() {
                return Transformations.switchMap(iTunesDao.search(query), searchData -> {
                    if (searchData == null) {
                        return AbsentLiveData.create();
                    } else {
                        return iTunesDao.loadOrdered(searchData.trackIds);
                    }
                });
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ResponseiTunesSearch>> createCall() {
                return iTunesService.searchItunes(query);
            }

            @Override
            protected ResponseiTunesSearch processResponse(ApiResponse<ResponseiTunesSearch> response) {
                ResponseiTunesSearch body = response.body;
                return body;
            }
        }.asLiveData();
    }
}

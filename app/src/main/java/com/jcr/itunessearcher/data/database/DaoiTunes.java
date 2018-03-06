package com.jcr.itunessearcher.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.util.SparseIntArray;

import com.jcr.itunessearcher.data.models.ResultiTune;
import com.jcr.itunessearcher.data.models.ResultiTunesSearch;

import java.util.Collections;
import java.util.List;

@Dao
public abstract class DaoiTunes {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(ResultiTune... iTune);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertRepos(List<ResultiTune> iTunes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(ResultiTunesSearch result);

    @Query("SELECT * FROM ResultiTunesSearch WHERE searchQuery = :searchQuery")
    public abstract LiveData<ResultiTunesSearch> search(String searchQuery);

    public LiveData<List<ResultiTune>> loadOrdered(List<Integer> trackIds) {
        SparseIntArray order = new SparseIntArray();
        int index = 0;
        for (Integer repoId : trackIds) {
            order.put(repoId, index++);
        }
        return Transformations.map(loadById(trackIds), repositories -> {
            Collections.sort(repositories, (r1, r2) -> {
                int pos1 = order.get(r1.trackId);
                int pos2 = order.get(r2.trackId);
                return pos1 - pos2;
            });
            return repositories;
        });
    }

    @Query("SELECT * FROM iTunes WHERE trackId in (:trackIds)")
    protected abstract LiveData<List<ResultiTune>> loadById(List<Integer> trackIds);

    @Query("SELECT * FROM iTunes WHERE trackId in (:trackId)")
    public abstract LiveData<ResultiTune> loadById(Integer trackId);

}

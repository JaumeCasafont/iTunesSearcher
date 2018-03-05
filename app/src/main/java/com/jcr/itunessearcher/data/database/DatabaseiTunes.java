package com.jcr.itunessearcher.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jcr.itunessearcher.data.models.ResultiTune;
import com.jcr.itunessearcher.data.models.ResultiTunesSearch;

@Database(entities = {ResultiTune.class, ResultiTunesSearch.class}, version = 1)
public abstract class DatabaseiTunes extends RoomDatabase {

    abstract public DaoiTunes iTunesDao();
}

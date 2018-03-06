package com.jcr.itunessearcher.ui.mediaplayer;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.jcr.itunessearcher.data.RepositoryiTunes;
import com.jcr.itunessearcher.data.models.ResultiTune;
import com.jcr.itunessearcher.util.AbsentLiveData;

import javax.inject.Inject;

public class MediaPlayerViewModel extends ViewModel {

    private final LiveData<ResultiTune> iTune;

    private MutableLiveData<Integer> trackId = new MutableLiveData<>();

    @Inject
    MediaPlayerViewModel(RepositoryiTunes iTunesRepository) {
        this.trackId = new MutableLiveData<>();
        iTune = Transformations.switchMap(trackId, id -> {
            if (id < 0) {
                return AbsentLiveData.create();
            }
            return iTunesRepository.getiTuneById(id);
        });
    }

    @VisibleForTesting
    public LiveData<ResultiTune> getiTune() {
        return iTune;
    }

    public void setTrackId(@NonNull Integer id) {
        trackId.setValue(id);
    }
}

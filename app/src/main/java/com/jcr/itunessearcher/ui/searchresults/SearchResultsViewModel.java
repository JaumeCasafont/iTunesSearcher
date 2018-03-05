package com.jcr.itunessearcher.ui.searchresults;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.jcr.itunessearcher.data.RepositoryiTunes;
import com.jcr.itunessearcher.data.models.Resource;
import com.jcr.itunessearcher.data.models.ResultiTune;
import com.jcr.itunessearcher.util.AbsentLiveData;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

public class SearchResultsViewModel extends ViewModel {

    private final MutableLiveData<String> query = new MutableLiveData<>();

    private final LiveData<Resource<List<ResultiTune>>> results;

    @Inject
    SearchResultsViewModel(RepositoryiTunes iTunesRepository) {
        results = Transformations.switchMap(query, search -> {
            if (search == null || search.trim().length() == 0) {
                return AbsentLiveData.create();
            } else {
                return iTunesRepository.search(search);
            }
        });
    }

    @VisibleForTesting
    public LiveData<Resource<List<ResultiTune>>> getResults() {
        return results;
    }

    public void setQuery(@NonNull String originalInput) {
        String input = originalInput.toLowerCase(Locale.getDefault()).trim();
        if (Objects.equals(input, query.getValue())) {
            return;
        }
        query.setValue(input);
    }

    void refresh() {
        if (query.getValue() != null) {
            query.setValue(query.getValue());
        }
    }
}

package com.jcr.itunessearcher.ui.searchresults;


import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jcr.itunessearcher.R;
import com.jcr.itunessearcher.data.models.ResultiTune;

import com.jcr.itunessearcher.databinding.ItuneItemBinding;
import com.jcr.itunessearcher.ui.DataBoundListAdapter;

import java.util.Objects;

public class SearchResultsListAdapter extends DataBoundListAdapter<ResultiTune, ItuneItemBinding> {
    private final android.databinding.DataBindingComponent dataBindingComponent;
    private final SearchResultsClickCallback resultsClickCallback;

    public SearchResultsListAdapter(DataBindingComponent dataBindingComponent,
                                    SearchResultsClickCallback resultsClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.resultsClickCallback = resultsClickCallback;
    }

    @Override
    protected ItuneItemBinding createBinding(ViewGroup parent) {
        ItuneItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.itune_item,
                        parent, false, dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {
            ResultiTune repo = binding.getITune();
            if (repo != null && resultsClickCallback != null) {
                resultsClickCallback.onClick(repo);
            }
        });
        return binding;
    }

    @Override
    protected void bind(ItuneItemBinding binding, ResultiTune item) {
        binding.setITune(item);
    }

    @Override
    protected boolean areItemsTheSame(ResultiTune oldItem, ResultiTune newItem) {
        return Objects.equals(oldItem.trackId, newItem.trackId);
    }

    @Override
    protected boolean areContentsTheSame(ResultiTune oldItem, ResultiTune newItem) {
        return Objects.equals(oldItem.artworkUrl30, newItem.artworkUrl30) &&
                Objects.equals(oldItem.artworkUrl60, newItem.artworkUrl60) &&
                Objects.equals(oldItem.artworkUrl100, newItem.artworkUrl100);
    }

    public interface SearchResultsClickCallback {
        void onClick(ResultiTune repo);
    }
}

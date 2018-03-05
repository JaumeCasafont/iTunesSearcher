package com.jcr.itunessearcher.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.jcr.itunessearcher.ui.MainViewModelFactory;
import com.jcr.itunessearcher.ui.searchresults.SearchResultsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchResultsViewModel.class)
    abstract ViewModel bindSearchResultsViewModel(SearchResultsViewModel userViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MainViewModelFactory factory);
}

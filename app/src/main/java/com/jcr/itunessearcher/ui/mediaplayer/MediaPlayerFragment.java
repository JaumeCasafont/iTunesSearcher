package com.jcr.itunessearcher.ui.mediaplayer;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcr.itunessearcher.R;
import com.jcr.itunessearcher.binding.FragmentDataBindingComponent;
import com.jcr.itunessearcher.databinding.MediaPlayerFragmentBinding;
import com.jcr.itunessearcher.databinding.SearchResultsFragmentBinding;
import com.jcr.itunessearcher.di.Injectable;
import com.jcr.itunessearcher.util.AutoClearedValue;

import java.io.IOException;

import javax.inject.Inject;

public class MediaPlayerFragment extends Fragment implements Injectable, MediaPlayer.OnPreparedListener {

    private static final String TRACK_ID_KEY = "track_id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    android.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    AutoClearedValue<MediaPlayerFragmentBinding> binding;

    private MediaPlayer mediaPlayer;
    private MediaPlayerViewModel mediaPlayerViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MediaPlayerFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.media_player_fragment, container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mediaPlayerViewModel = ViewModelProviders.of(this, viewModelFactory).get(MediaPlayerViewModel.class);
        Bundle args = getArguments();
        if (args != null && args.containsKey(TRACK_ID_KEY)) {
            mediaPlayerViewModel.setTrackId(args.getInt(TRACK_ID_KEY));
        }
        mediaPlayerViewModel.getiTune().observe(this, resultiTune -> {
            if (resultiTune != null) {
                binding.get().setITune(resultiTune);
                setupMediaPlayer(resultiTune.previewUrl);
            }
        });

    }

    private void setupMediaPlayer(String url) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(this);
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public static MediaPlayerFragment create(Integer trackId) {
        MediaPlayerFragment mediaPlayerFragment = new MediaPlayerFragment();
        Bundle args = new Bundle();
        args.putInt(TRACK_ID_KEY, trackId);
        mediaPlayerFragment.setArguments(args);
        return mediaPlayerFragment;
    }
}

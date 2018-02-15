package com.example.micha.projectmcqueen.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 30.01.2018.
 */

public class AlphabethViewModel extends ViewModel {
    //region ExoPlayer
    private SimpleExoPlayer exoPlayer;
    private ExoPlayer.EventListener exoPlayerListener;
    private final List<Uri> playlist = new ArrayList<>();

    public void initializePlayer(Context context) {
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
        }
    }

    public void releasePlayer() {
        clearPlaylist();
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }


    private void playAudio(Context context, Uri mediaUri) {
        exoPlayer.stop();

        String userAgent = Util.getUserAgent(context, "ProjectMcQueen");
        MediaSource mediaSource = new ExtractorMediaSource(
                mediaUri,
                new DefaultDataSourceFactory(context, userAgent),
                new DefaultExtractorsFactory(),
                null,
                null);

        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    public void playSingleAudio(Context context, String uri) {
        clearPlaylist();

        playAudio(context, Uri.parse(uri));
    }

    public void playAudioList(Context context, List<String> uriS) {
        clearPlaylist();

        for (String uri : uriS) {
            playlist.add(Uri.parse(uri));
        }
        exoPlayerListener = new ExoPlayer.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState) {
                    case ExoPlayer.STATE_ENDED:
                        if (playlist.size() > 0) {
                            Uri uri = playlist.get(0);
                            playlist.remove(uri);
                            playAudio(context, uri);
                        } else {
                            clearPlaylist();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity() {

            }
        };
        exoPlayer.addListener(exoPlayerListener);
        if (playlist.size() > 0) {
            Uri uri = playlist.get(0);
            playlist.remove(uri);
            playAudio(context, uri);
        }
    }


    public void clearPlaylist() {
        if (exoPlayerListener != null) {
            exoPlayer.removeListener(exoPlayerListener);
            exoPlayerListener = null;
        }
        if (playlist.size() > 0) {
            playlist.clear();
        }
        exoPlayer.stop();
    }
    //endregion

    private MutableLiveData<Integer> selectedLetterIndex;
    private MutableLiveData<Integer> selectedItemIndex;


    public LiveData<Integer> getSelectedLetterIndex() {
        if (selectedLetterIndex == null) {
            selectedLetterIndex = new MutableLiveData<>();
            selectedLetterIndex.postValue(0);
        }
        return selectedLetterIndex;
    }

    public LiveData<Integer> getSelectedItemIndex() {
        if (selectedItemIndex == null) {
            selectedItemIndex = new MutableLiveData<>();
            selectedItemIndex.postValue(-1);
        }
        return selectedItemIndex;
    }

    public void selectLetterIndex(int index) {
        selectedLetterIndex.postValue(index);
        selectItemIndex(-1);
    }

    public void selectItemIndex(int index) {
        selectedItemIndex.postValue(index);
    }


}

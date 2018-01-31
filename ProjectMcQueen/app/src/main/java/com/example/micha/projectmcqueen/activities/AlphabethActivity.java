package com.example.micha.projectmcqueen.activities;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.micha.projectmcqueen.R;
import com.example.micha.projectmcqueen.adapters.AlphabethAdapter;
import com.example.micha.projectmcqueen.databinding.ActivityAlphabethBinding;
import com.example.micha.projectmcqueen.models.AlphabethItem;
import com.example.micha.projectmcqueen.viewmodels.AlphabethViewModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class AlphabethActivity extends AppCompatActivity implements AlphabethAdapter.AlphabethAdapterOnClickHandler {
    private static final String LETTER_INDEX_KEY = "letter-index";
    private static final String ITEM_INDEX_KEY = "item-index";
    private static final int UI_ANIMATION_DELAY = 300;

    private ActivityAlphabethBinding binding;
    private AlphabethAdapter adapter;
    private AlphabethViewModel viewModel;

    private SimpleExoPlayer exoPlayer;

    private final Handler hideHandler = new Handler();
    private final Runnable hideRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            binding.llMain.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_alphabeth);
        binding.alphabethTopPart.tvLetters.setTypeface(Typeface.createFromAsset(getAssets(), "GloriaHallelujah.ttf"));
        binding.alphabethBottomPart.textView.setTypeface(Typeface.createFromAsset(getAssets(), "GloriaHallelujah.ttf"));

        binding.alphabethTopPart.rvLetters.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.alphabethTopPart.rvLetters.setHasFixedSize(true);
        adapter = new AlphabethAdapter(this, this);
        binding.alphabethTopPart.rvLetters.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(AlphabethViewModel.class);
        setViewModelObservers();

        if (savedInstanceState != null) {
            int letterIndex = savedInstanceState.getInt(LETTER_INDEX_KEY);
            viewModel.selectLetterIndex(letterIndex);

            int itemIndex = savedInstanceState.getInt(ITEM_INDEX_KEY);
            viewModel.selectItemIndex(itemIndex);
        }

        initializePlayer();

        binding.alphabethTopPart.tvLetters.setOnClickListener(v -> {
            AlphabethItem alphabethItem = adapter.getiItem(viewModel.getSelectedLetterIndex().getValue());
            playAudio(Uri.parse(alphabethItem.getLetterAudioUri()));
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LETTER_INDEX_KEY, viewModel.getSelectedLetterIndex().getValue());
        outState.putInt(ITEM_INDEX_KEY, viewModel.getSelectedItemIndex().getValue());
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeActivityFullscreen();
    }

    private void makeActivityFullscreen() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        hideHandler.removeCallbacks(hideRunnable);
        hideHandler.postDelayed(hideRunnable, UI_ANIMATION_DELAY);
    }

    //region Clicks
    @Override
    public void onLetterClick(int index) {
        viewModel.selectLetterIndex(index);
        AlphabethItem alphabethItem = adapter.getiItem(index);
        playAudio(Uri.parse(alphabethItem.getLetterAudioUri()));
    }

    public void ib_main_clicked(View view) {
        viewModel.selectItemIndex(-1);
        exoPlayer.stop();
    }

    public void ib_01_clicked(View view) {
        viewModel.selectItemIndex(0);
//        playAudio(Uri.parse("asset:///letters/Monk's theme song instrumental.mp3"));
    }

    public void ib_02_clicked(View view) {
        viewModel.selectItemIndex(1);
//        playAudio(Uri.parse("asset:///letters/Monk's theme song instrumental.mp3"));
    }

    public void ib_03_clicked(View view) {
        viewModel.selectItemIndex(2);
//        playAudio(Uri.parse("asset:///letters/Monk's theme song instrumental.mp3"));
    }

    public void ib_04_clicked(View view) {
        viewModel.selectItemIndex(3);
//        playAudio(Uri.parse("asset:///letters/Monk's theme song instrumental.mp3"));
    }

    public void ib_05_clicked(View view) {
        viewModel.selectItemIndex(4);
//        playAudio(Uri.parse("asset:///letters/Monk's theme song instrumental.mp3"));
    }
    //endregion Clicks

    private void setViewModelObservers() {
        viewModel.getSelectedLetterIndex().observe(this, letterIndex -> {
            AlphabethItem alphabethItem = adapter.getiItem(letterIndex);

            String letter = alphabethItem.letter;
            binding.alphabethTopPart.tvLetters.setText(letter.toUpperCase() + letter.toLowerCase());

            binding.alphabethBottomPart.ib01.setImageResource(alphabethItem.firstImageResId);
            binding.alphabethBottomPart.ib02.setImageResource(alphabethItem.secondImageResId);
            binding.alphabethBottomPart.ib03.setImageResource(alphabethItem.thirdImageResId);
            binding.alphabethBottomPart.ib04.setImageResource(alphabethItem.fourthImageResId);
            binding.alphabethBottomPart.ib05.setImageResource(alphabethItem.fifthImageResId);
        });

        viewModel.getSelectedItemIndex().observe(this, itemIndex -> {
            AlphabethItem alphabethItem = adapter.getiItem(viewModel.getSelectedLetterIndex().getValue());

            switch (itemIndex) {
                case 0:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.firstImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.firstImageResId);
                    binding.alphabethTopPart.ibMain.setVisibility(View.VISIBLE);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                case 1:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.secondImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.secondImageResId);
                    binding.alphabethTopPart.ibMain.setVisibility(View.VISIBLE);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                case 2:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.thirdImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.thirdImageResId);
                    binding.alphabethTopPart.ibMain.setVisibility(View.VISIBLE);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                case 3:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.fourthImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.fourthImageResId);
                    binding.alphabethTopPart.ibMain.setVisibility(View.VISIBLE);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                case 4:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.fifthImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.fifthImageResId);
                    binding.alphabethTopPart.ibMain.setVisibility(View.VISIBLE);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                default:
                    binding.alphabethBottomPart.textView.setText("");
                    binding.alphabethTopPart.ibMain.setImageResource(0);
                    binding.alphabethTopPart.ibMain.setVisibility(View.GONE);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }

    private void initializePlayer() {
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    private void playAudio(Uri mediaUri) {
        exoPlayer.stop();
//        exoPlayer.release();

        String userAgent = Util.getUserAgent(this, "ProjectMcQueen");
        MediaSource mediaSource = new ExtractorMediaSource(
                mediaUri,
                new DefaultDataSourceFactory(this, userAgent),
                new DefaultExtractorsFactory(),
                null,
                null);

        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }
}

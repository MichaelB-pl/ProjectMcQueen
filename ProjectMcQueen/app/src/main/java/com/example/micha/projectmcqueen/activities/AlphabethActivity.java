package com.example.micha.projectmcqueen.activities;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.micha.projectmcqueen.R;
import com.example.micha.projectmcqueen.databinding.ActivityAlphabethBinding;
import com.example.micha.projectmcqueen.fragments.AlphabethMainFragment;
import com.example.micha.projectmcqueen.viewmodels.AlphabethViewModel;

public class AlphabethActivity extends AppCompatActivity {
    private static final int UI_ANIMATION_DELAY = 300;

    private ActivityAlphabethBinding binding;
    private AlphabethViewModel viewModel;

    private final Handler hideHandler = new Handler();
    private final Runnable hideRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            binding.flFragmentContainer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_alphabeth);
        viewModel = ViewModelProviders.of(this).get(AlphabethViewModel.class);

        View view = findViewById(R.id.fl_fragment_container);
        if (view != null) {
            if (savedInstanceState != null) {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment_container, AlphabethMainFragment.newInstance()).commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.releasePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeActivityFullscreen();
        viewModel.initializePlayer(this);
    }

    @Override
    public void onBackPressed() {
        viewModel.getExoPlayer().stop();
        super.onBackPressed();
    }

    private void makeActivityFullscreen() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        hideHandler.removeCallbacks(hideRunnable);
        hideHandler.postDelayed(hideRunnable, UI_ANIMATION_DELAY);
    }
}

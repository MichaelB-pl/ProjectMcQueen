package com.example.micha.projectmcqueen.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.tv.TvContentRating;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.micha.projectmcqueen.R;
import com.example.micha.projectmcqueen.databinding.FragmentAlphabethSpellBinding;
import com.example.micha.projectmcqueen.models.Alphabeth;
import com.example.micha.projectmcqueen.models.AlphabethItem;
import com.example.micha.projectmcqueen.viewmodels.AlphabethViewModel;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 15.02.2018.
 */

public class AlphabethSpellFragment extends Fragment {
    private static final int ANIMATION_LENGTH = 1000;
    private static final int ANIMATION_DELAY = 2000;

    private ExoPlayer.EventListener exoPlayerListener;
    private final List<Uri> playlist = new ArrayList<>();

    FragmentAlphabethSpellBinding binding;
    AlphabethViewModel viewModel;

    private AlphabethItem alphabethItem;
    private boolean loaded;

    public static AlphabethSpellFragment newInstance() {
        AlphabethSpellFragment fragment = new AlphabethSpellFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alphabeth_spell, null, true);
        setTypefaceForTextViews();

        alphabethItem = Alphabeth.getAlphabeth().get(viewModel.getSelectedLetterIndex().getValue());
        int index = viewModel.getSelectedItemIndex().getValue();
        binding.tvCompleteTextBlind.setText(alphabethItem.getImageName(index));
        List<String> uris = alphabethItem.getItemLettersAudioUri(index);
        uris.add(alphabethItem.getItemAudioUri(index));

        final ViewTreeObserver viewTreeObserver = binding.tvMovingLetter.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(() -> {
            int[] bigLetterLocation = new int[2];
            int[] movingLetterLocation = new int[2];
            binding.tvLetterBlind.getLocationOnScreen(bigLetterLocation);
            binding.tvMovingLetter.getLocationOnScreen(movingLetterLocation);

            int xToMove = bigLetterLocation[0] - movingLetterLocation[0];
            int yToMove = bigLetterLocation[1] - movingLetterLocation[1];

            if (!loaded && xToMove != 0 && yToMove != 0) {
                loaded = true;

                playAudioList(uris);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(AlphabethViewModel.class);
    }

    @Override
    public void onPause() {
        exitFragment();
        super.onPause();
    }

    private void setTypefaceForTextViews() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "Elementarz2.ttf");
        binding.tvCompleteTextBlind.setTypeface(typeface);
        binding.tvLetterBlind.setTypeface(typeface);
        binding.tvMovingLetter.setTypeface(typeface);
        binding.tvText.setTypeface(typeface);
    }

    private void playAudioList(List<String> uriS) {
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
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    final Handler handler = new Handler();
                    handler.postDelayed(() -> {
                    }, ANIMATION_DELAY);
                    animateLetter();
                }
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
            }

            @Override
            public void onPositionDiscontinuity() {

            }
        };
        viewModel.getExoPlayer().addListener(exoPlayerListener);
        tryPlayPlaylistItem();
    }

    private void animateLetter() {
        final float bigLetter = binding.tvLetterBlind.getTextSize();
        final float smallLetter = binding.tvCompleteTextBlind.getTextSize();
        ValueAnimator textSizeAnimator = ValueAnimator.ofFloat(bigLetter, smallLetter);
        textSizeAnimator.setDuration(ANIMATION_LENGTH);
        textSizeAnimator.addUpdateListener(valueAnimator -> {
            float textSize = (float) valueAnimator.getAnimatedValue();
            binding.tvMovingLetter.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            if (textSize == smallLetter) {
                tryPlayPlaylistItem();
            }
        });

        final float startX = binding.tvMovingLetter.getTranslationX();
        final float startY = binding.tvMovingLetter.getTranslationY();
        ValueAnimator xAnimator = ValueAnimator.ofFloat(startX, 0);
        ValueAnimator yAnimator = ValueAnimator.ofFloat(startY, 0);
        xAnimator.setDuration(ANIMATION_LENGTH);
        yAnimator.setDuration(ANIMATION_LENGTH);
        xAnimator.addUpdateListener(valueAnimator -> {
            float currentX = (float) valueAnimator.getAnimatedValue();
            binding.tvMovingLetter.setTranslationX(currentX);
        });
        yAnimator.addUpdateListener(valueAnimator -> {
            float currentY = (float) valueAnimator.getAnimatedValue();
            binding.tvMovingLetter.setTranslationY(currentY);
        });

        int startColor = Color.BLACK;
        int endColor = Color.WHITE;
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            startColor = getResources().getColor(R.color.colorAccent, null);
        } else {
            startColor = getResources().getColor(R.color.colorAccent);
        }*/
        ValueAnimator colorAnimator = ValueAnimator.ofInt(startColor, endColor);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(ANIMATION_LENGTH);
        colorAnimator.addUpdateListener(valueAnimator -> {
            int color = (int) valueAnimator.getAnimatedValue();
            binding.tvMovingLetter.setTextColor(color);
        });

        xAnimator.start();
        yAnimator.start();
        colorAnimator.start();
        textSizeAnimator.start();
    }

    private void setMovingLetterStartPosition() {
        binding.tvMovingLetter.measure(0, 0);
        float textSize = binding.tvLetterBlind.getTextSize();
        int[] bigLetterLocation = new int[2];
        int[] movingLetterLocation = new int[2];
        binding.tvLetterBlind.getLocationInWindow(bigLetterLocation);
        binding.tvMovingLetter.getLocationInWindow(movingLetterLocation);

        int xToMove = bigLetterLocation[0] - movingLetterLocation[0];
        int yToMove = bigLetterLocation[1] - movingLetterLocation[1];
        xToMove -= binding.tvMovingLetter.getMeasuredWidth();
        yToMove += (binding.tvLetterBlind.getMeasuredHeight() - binding.tvMovingLetter.getMeasuredHeight()) / 2;

        int color = Color.BLACK;
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getResources().getColor(R.color.colorAccent, null);
        } else {
            color = getResources().getColor(R.color.colorAccent);
        }*/

        binding.tvMovingLetter.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        binding.tvMovingLetter.setTranslationX((float) xToMove);
        binding.tvMovingLetter.setTranslationY((float) yToMove);
        binding.tvMovingLetter.setTextColor(color);

        binding.tvMovingLetter.setScaleX(0);
        binding.tvMovingLetter.setScaleY(0);
    }

    private void tryPlayPlaylistItem() {
        if (playlist.size() > 0) {
            Uri uri = playlist.get(0);
            playlist.remove(uri);
            String path = uri.getLastPathSegment();
            String[] words = path.split(".mp3");
            if (words[0].length() > 1) {
                int index = viewModel.getSelectedItemIndex().getValue();
                int imgRes = alphabethItem.getImageResId(index);
                binding.imageView.setImageResource(imgRes);
                binding.tvLetterBlind.setText("");
                binding.tvText.setText(alphabethItem.getImageName(index));
                binding.tvMovingLetter.setText("");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    binding.tvText.setTransitionName(getResources().getString(R.string.tv_alphabeth_image_name_transition_name));
                    binding.imageView.setTransitionName(getResources().getString(R.string.iv_alphabeth_transition_name));
                }

                binding.imageView.setScaleX(0);
                binding.imageView.setScaleY(0);
                ValueAnimator scaleAnimator = ValueAnimator.ofFloat(0, 0.25f, 0.5f, 0.75f, 1, 1.1f, 1);
                scaleAnimator.setDuration(ANIMATION_LENGTH);
                scaleAnimator.addUpdateListener(valueAnimator -> {
                    float scale = (float) valueAnimator.getAnimatedValue();
                    binding.imageView.setScaleX(scale);
                    binding.imageView.setScaleY(scale);
                    if (scale < 1) {
                        binding.tvText.setScaleX(1);
                        binding.tvText.setScaleY(1);
                    } else {
                        binding.tvText.setScaleX(scale);
                        binding.tvText.setScaleY(scale);
                    }
                });
                scaleAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        viewModel.playAudio(getContext(), uri);
                    }
                });
                scaleAnimator.start();
            } else {
                String letter = words[0];
                String actualText = binding.tvText.getText().toString();
                String lastLetter = binding.tvMovingLetter.getText().toString();
                if (lastLetter.length() == 0) {
                    letter = letter.toUpperCase();
                }
                binding.tvText.setText(actualText + lastLetter);
                binding.tvMovingLetter.setText(letter);
                binding.tvLetterBlind.setText(letter);

                setMovingLetterStartPosition();

                ValueAnimator scaleAnimator = ValueAnimator.ofFloat(0, 0.25f, 0.5f, 0.75f, 1, 1.1f, 1);
                scaleAnimator.setDuration(ANIMATION_LENGTH / 2);
                scaleAnimator.addUpdateListener(valueAnimator -> {
                    float scale = (float) valueAnimator.getAnimatedValue();
                    binding.tvMovingLetter.setScaleX(scale);
                    binding.tvMovingLetter.setScaleY(scale);
                });
                scaleAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        viewModel.playAudio(getContext(), uri);
                    }
                });
                scaleAnimator.start();
            }
        } else {
            exitFragment();
        }
    }

    private void clearPlaylist() {
        if (exoPlayerListener != null) {
            viewModel.getExoPlayer().removeListener(exoPlayerListener);
            exoPlayerListener = null;
        }
        if (playlist.size() > 0) {
            playlist.clear();
        }
        viewModel.getExoPlayer().stop();
    }

    private void exitFragment() {
        clearPlaylist();
        try {
            getFragmentManager().popBackStack();
        } catch (NullPointerException ex) {
        }
    }
}

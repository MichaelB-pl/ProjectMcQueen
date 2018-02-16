package com.example.micha.projectmcqueen.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.micha.projectmcqueen.R;
import com.example.micha.projectmcqueen.adapters.AlphabethAdapter;
import com.example.micha.projectmcqueen.databinding.FragmentAlphabethMainBinding;
import com.example.micha.projectmcqueen.models.AlphabethItem;
import com.example.micha.projectmcqueen.viewmodels.AlphabethViewModel;

import java.util.List;

/**
 * Created by micha on 15.02.2018.
 */

public class AlphabethMainFragment extends Fragment implements AlphabethAdapter.AlphabethAdapterOnClickHandler {
    private static final String LETTER_INDEX_KEY = "letter-index";
    private static final String ITEM_INDEX_KEY = "item-index";

    private FragmentAlphabethMainBinding binding;
    private AlphabethViewModel viewModel;
    private AlphabethAdapter adapter;

    public static AlphabethMainFragment newInstance() {
        AlphabethMainFragment fragment = new AlphabethMainFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alphabeth_main, null, true);
        binding.alphabethTopPart.tvLetters.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "GloriaHallelujah.ttf"));
        binding.alphabethBottomPart.textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "GloriaHallelujah.ttf"));

        binding.alphabethTopPart.rvLetters.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.alphabethTopPart.rvLetters.setHasFixedSize(true);
        adapter = new AlphabethAdapter(getContext(), this);
        binding.alphabethTopPart.rvLetters.setAdapter(adapter);

        setViewModelObservers();
        setClickListeners();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(AlphabethViewModel.class);


        if (savedInstanceState != null) {
            int letterIndex = savedInstanceState.getInt(LETTER_INDEX_KEY);
            viewModel.selectLetterIndex(letterIndex);

            int itemIndex = savedInstanceState.getInt(ITEM_INDEX_KEY);
            viewModel.selectItemIndex(itemIndex);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LETTER_INDEX_KEY, viewModel.getSelectedLetterIndex().getValue());
        outState.putInt(ITEM_INDEX_KEY, viewModel.getSelectedItemIndex().getValue());
    }


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

    private void trySelectItemIndex(int index) {
        AlphabethItem alphabethItem = adapter.getiItem(viewModel.getSelectedLetterIndex().getValue());
        if (!alphabethItem.isItemEmpty(index)) {
            viewModel.selectItemIndex(index);
            viewModel.playAudio(getContext(), Uri.parse(alphabethItem.getItemAudioUri(index)));
        }
    }

    //region Clicks
    @Override
    public void onLetterClick(int index) {
        viewModel.selectLetterIndex(index);
        AlphabethItem alphabethItem = adapter.getiItem(index);
        viewModel.playAudio(getContext(), Uri.parse(alphabethItem.getLetterAudioUri()));
    }

    private void setClickListeners() {
        binding.alphabethTopPart.tvLetters.setOnClickListener(v -> {
            AlphabethItem alphabethItem = adapter.getiItem(viewModel.getSelectedLetterIndex().getValue());
            viewModel.playAudio(getContext(), Uri.parse(alphabethItem.getLetterAudioUri()));
        });
        binding.alphabethBottomPart.textView.setOnClickListener(view -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                String tl = binding.alphabethBottomPart.linearLayout.getTransitionName();
                transaction.addSharedElement(binding.alphabethBottomPart.linearLayout, binding.alphabethBottomPart.linearLayout.getTransitionName());
                transaction.addSharedElement(binding.alphabethTopPart.ibMain, binding.alphabethTopPart.ibMain.getTransitionName());
                transaction.addSharedElement(binding.alphabethBottomPart.textView, binding.alphabethBottomPart.textView.getTransitionName());
            }

            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

            transaction.replace(R.id.fl_fragment_container, AlphabethSpellFragment.newInstance());
            transaction.addToBackStack(null);
//transaction.commitAllowingStateLoss();

            transaction.commit();
        });
        binding.alphabethTopPart.ibMain.setOnClickListener(view -> {
            viewModel.selectItemIndex(-1);
            viewModel.getExoPlayer().stop();
        });

        binding.alphabethBottomPart.ib01.setOnClickListener(view -> trySelectItemIndex(0));
        binding.alphabethBottomPart.ib02.setOnClickListener(view -> trySelectItemIndex(1));
        binding.alphabethBottomPart.ib03.setOnClickListener(view -> trySelectItemIndex(2));
        binding.alphabethBottomPart.ib04.setOnClickListener(view -> trySelectItemIndex(3));
        binding.alphabethBottomPart.ib05.setOnClickListener(view -> trySelectItemIndex(4));
    }
    //endregion Clicks
}

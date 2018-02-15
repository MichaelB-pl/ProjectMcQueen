package com.example.micha.projectmcqueen.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.micha.projectmcqueen.R;
import com.example.micha.projectmcqueen.databinding.FragmentAlphabethSpellBinding;
import com.example.micha.projectmcqueen.models.Alphabeth;
import com.example.micha.projectmcqueen.models.AlphabethItem;
import com.example.micha.projectmcqueen.viewmodels.AlphabethViewModel;

import java.util.List;

/**
 * Created by micha on 15.02.2018.
 */

public class AlphabethSpellFragment extends Fragment {

    FragmentAlphabethSpellBinding binding;
    AlphabethViewModel viewModel;

    public static AlphabethSpellFragment newInstance() {
        AlphabethSpellFragment fragment = new AlphabethSpellFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alphabeth_spell, null, true);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(AlphabethViewModel.class);
    }

    @Override
    public void onPause() {
        viewModel.clearPlaylist();
        getFragmentManager().popBackStack();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        AlphabethItem alphabethItem = Alphabeth.getAlphabeth().get(viewModel.getSelectedLetterIndex().getValue());
        int index = viewModel.getSelectedItemIndex().getValue();
        List<String> uris = alphabethItem.getItemLettersAudioUri(index);
        uris.add(alphabethItem.getItemAudioUri(index));
        viewModel.playAudioList(getContext(), uris);
    }
}

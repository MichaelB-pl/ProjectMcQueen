package com.example.micha.projectmcqueen.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.micha.projectmcqueen.adapters.AlphabethAdapter;
import com.example.micha.projectmcqueen.models.AlphabethItem;
import com.example.micha.projectmcqueen.R;
import com.example.micha.projectmcqueen.databinding.ActivityAlphabethBinding;
import com.example.micha.projectmcqueen.viewmodels.AlphabethViewModel;

public class AlphabethActivity extends AppCompatActivity implements AlphabethAdapter.AlphabethAdapterOnClickHandler {
    private static final String LETTER_INDEX_KEY = "letter-index";
    private static final String ITEM_INDEX_KEY = "item-index";

    private ActivityAlphabethBinding binding;
    private AlphabethAdapter adapter;
    private AlphabethViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_alphabeth);
        binding.alphabethTopPart.tvLetters.setTypeface(Typeface.createFromAsset(getAssets(), "GloriaHallelujah.ttf"));
        binding.alphabethBottomPart.textView.setTypeface(Typeface.createFromAsset(getAssets(), "GloriaHallelujah.ttf"));

        binding.alphabethTopPart.rvLetters.setLayoutManager(new LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, false));
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
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LETTER_INDEX_KEY, viewModel.getSelectedLetterIndex().getValue());
        outState.putInt(ITEM_INDEX_KEY, viewModel.getSelectedItemIndex().getValue());
    }

    @Override
    public void onLetterClick(int index) {
        viewModel.selectLetterIndex(index);
    }

    public void ib_main_clicked(View view) {
        viewModel.selectItemIndex(-1);
    }

    public void ib_01_clicked(View view) {
        viewModel.selectItemIndex(0);
    }

    public void ib_02_clicked(View view) {
        viewModel.selectItemIndex(1);
    }

    public void ib_03_clicked(View view) {
        viewModel.selectItemIndex(2);
    }

    public void ib_04_clicked(View view) {
        viewModel.selectItemIndex(3);
    }

    public void ib_05_clicked(View view) {
        viewModel.selectItemIndex(4);
    }

    private void setViewModelObservers() {
        viewModel.getSelectedLetterIndex().observe(this, letterIndex -> {
            AlphabethItem alphabethItem = adapter.getiItem(letterIndex);

            String letter = alphabethItem.Letter;
            binding.alphabethTopPart.tvLetters.setText(letter.toUpperCase() + letter.toLowerCase());

            binding.alphabethBottomPart.ib01.setImageResource(alphabethItem.FirstImageResId);
            binding.alphabethBottomPart.ib02.setImageResource(alphabethItem.SecondImageResId);
            binding.alphabethBottomPart.ib03.setImageResource(alphabethItem.ThirdImageResId);
            binding.alphabethBottomPart.ib04.setImageResource(alphabethItem.FourthImageResId);
            binding.alphabethBottomPart.ib05.setImageResource(alphabethItem.FifthImageResId);
        });

        viewModel.getSelectedItemIndex().observe(this, itemIndex -> {
            AlphabethItem alphabethItem = adapter.getiItem(viewModel.getSelectedLetterIndex().getValue());

            switch (itemIndex) {
                case 0:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.FirstImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.FirstImageResId);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                case 1:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.SecondImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.SecondImageResId);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                case 2:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.ThirdImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.ThirdImageResId);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                case 3:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.FourthImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.FourthImageResId);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                case 4:
                    binding.alphabethBottomPart.textView.setText(alphabethItem.FifthImageName);
                    binding.alphabethTopPart.ibMain.setImageResource(alphabethItem.FifthImageResId);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.GONE);
                    break;
                default:
                    binding.alphabethBottomPart.textView.setText("");
                    binding.alphabethTopPart.ibMain.setImageResource(0);
                    binding.alphabethTopPart.tvLetters.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }
}

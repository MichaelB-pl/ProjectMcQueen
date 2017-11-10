package com.example.micha.projectmcqueen;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.micha.projectmcqueen.Adapters.AlphabethAdapter;
import com.example.micha.projectmcqueen.databinding.ActivityAlphabethBinding;

public class AlphabethActivity extends AppCompatActivity implements AlphabethAdapter.AlphabethAdapterOnClickHandler {
    private static final String LIST_INDEX_KEY = "list-index";
    private static final String ITEM_INDEX_KEY = "item-index";

    private ActivityAlphabethBinding mBinding;
    private AlphabethAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_alphabeth);

//        if(savedInstanceState == null){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mBinding.alphabethTopPart.rvLetters.setLayoutManager(layoutManager);
        mBinding.alphabethTopPart.rvLetters.setHasFixedSize(true);
        mAdapter = new AlphabethAdapter(this, this);
        mBinding.alphabethTopPart.rvLetters.setAdapter(mAdapter);
//        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIST_INDEX_KEY)) {
                int listIndex = savedInstanceState.getInt(LIST_INDEX_KEY);
                mAdapter.ListIndex = listIndex;
            }
            if (savedInstanceState.containsKey(ITEM_INDEX_KEY)) {
                int itemIndex = savedInstanceState.getInt(ITEM_INDEX_KEY);
                mAdapter.ItemIndex = itemIndex;
            }
        }

        mAdapter.LoadSelectedItem();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LIST_INDEX_KEY, mAdapter.ListIndex);
        outState.putInt(ITEM_INDEX_KEY, mAdapter.ItemIndex);
    }

    @Override
    public void onClick(int index) {
        refreshUI();
    }

    public void ib_main_clicked(View view) {
        mAdapter.ItemIndex = -1;
        refreshUI();
    }

    public void ib_01_clicked(View view) {
        mAdapter.ItemIndex = 0;
        refreshUI();
    }

    public void ib_02_clicked(View view) {
        mAdapter.ItemIndex = 1;
        refreshUI();
    }

    public void ib_03_clicked(View view) {
        mAdapter.ItemIndex = 2;
        refreshUI();
    }

    public void ib_04_clicked(View view) {
        mAdapter.ItemIndex = 3;
        refreshUI();
    }

    public void ib_05_clicked(View view) {
        mAdapter.ItemIndex = 4;
        refreshUI();
    }

    private void refreshUI() {
        int listIndex = mAdapter.ListIndex;
        int itemIndex = mAdapter.ItemIndex;

        String letter = mAdapter.mAlphabeth[listIndex].Letter;
        mBinding.alphabethTopPart.tvLetters.setText(letter.toUpperCase() + letter.toLowerCase());

        if (itemIndex == 0) {
            mBinding.alphabethBottomPart.textView.setText(mAdapter.mAlphabeth[listIndex].FirstImageName);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FirstImageResId).into(mBinding.alphabethTopPart.ibMain);
            Glide.with(this).load(R.drawable.transparent_rectangle).into(mBinding.alphabethBottomPart.ib01);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].SecondImageResId).into(mBinding.alphabethBottomPart.ib02);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].ThirdImageResId).into(mBinding.alphabethBottomPart.ib03);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FourthImageResId).into(mBinding.alphabethBottomPart.ib04);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FifthImageResId).into(mBinding.alphabethBottomPart.ib05);
        } else if (itemIndex == 1) {
            mBinding.alphabethBottomPart.textView.setText(mAdapter.mAlphabeth[listIndex].SecondImageName);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].SecondImageResId).into(mBinding.alphabethTopPart.ibMain);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FirstImageResId).into(mBinding.alphabethBottomPart.ib01);
            Glide.with(this).load(R.drawable.transparent_rectangle).into(mBinding.alphabethBottomPart.ib02);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].ThirdImageResId).into(mBinding.alphabethBottomPart.ib03);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FourthImageResId).into(mBinding.alphabethBottomPart.ib04);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FifthImageResId).into(mBinding.alphabethBottomPart.ib05);
        } else if (itemIndex == 2) {
            mBinding.alphabethBottomPart.textView.setText(mAdapter.mAlphabeth[listIndex].ThirdImageName);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].ThirdImageResId).into(mBinding.alphabethTopPart.ibMain);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FirstImageResId).into(mBinding.alphabethBottomPart.ib01);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].SecondImageResId).into(mBinding.alphabethBottomPart.ib02);
            Glide.with(this).load(R.drawable.transparent_rectangle).into(mBinding.alphabethBottomPart.ib03);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FourthImageResId).into(mBinding.alphabethBottomPart.ib04);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FifthImageResId).into(mBinding.alphabethBottomPart.ib05);
        } else if (itemIndex == 3) {
            mBinding.alphabethBottomPart.textView.setText(mAdapter.mAlphabeth[listIndex].FourthImageName);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FourthImageResId).into(mBinding.alphabethTopPart.ibMain);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FirstImageResId).into(mBinding.alphabethBottomPart.ib01);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].SecondImageResId).into(mBinding.alphabethBottomPart.ib02);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].ThirdImageResId).into(mBinding.alphabethBottomPart.ib03);
            Glide.with(this).load(R.drawable.transparent_rectangle).into(mBinding.alphabethBottomPart.ib04);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FifthImageResId).into(mBinding.alphabethBottomPart.ib05);
        } else if (itemIndex == 4) {
            mBinding.alphabethBottomPart.textView.setText(mAdapter.mAlphabeth[listIndex].FifthImageName);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FifthImageResId).into(mBinding.alphabethTopPart.ibMain);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FirstImageResId).into(mBinding.alphabethBottomPart.ib01);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].SecondImageResId).into(mBinding.alphabethBottomPart.ib02);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].ThirdImageResId).into(mBinding.alphabethBottomPart.ib03);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FourthImageResId).into(mBinding.alphabethBottomPart.ib04);
            Glide.with(this).load(R.drawable.transparent_rectangle).into(mBinding.alphabethBottomPart.ib05);
        } else {
            mBinding.alphabethBottomPart.textView.setText("");
            Glide.with(this).load(R.drawable.transparent_rectangle).into(mBinding.alphabethTopPart.ibMain);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FirstImageResId).into(mBinding.alphabethBottomPart.ib01);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].SecondImageResId).into(mBinding.alphabethBottomPart.ib02);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].ThirdImageResId).into(mBinding.alphabethBottomPart.ib03);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FourthImageResId).into(mBinding.alphabethBottomPart.ib04);
            Glide.with(this).load(mAdapter.mAlphabeth[listIndex].FifthImageResId).into(mBinding.alphabethBottomPart.ib05);
        }
    }
}

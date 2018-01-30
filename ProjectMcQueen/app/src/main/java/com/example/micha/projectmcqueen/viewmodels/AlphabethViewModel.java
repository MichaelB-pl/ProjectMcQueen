package com.example.micha.projectmcqueen.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by micha on 30.01.2018.
 */

public class AlphabethViewModel extends ViewModel {
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

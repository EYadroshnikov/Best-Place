package com.example.bestplace_jav2.ui.bestplaces;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BestPlacesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BestPlacesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
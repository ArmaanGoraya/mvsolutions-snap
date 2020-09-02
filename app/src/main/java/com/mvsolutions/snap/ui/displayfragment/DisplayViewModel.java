package com.mvsolutions.snap.ui.displayfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DisplayViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DisplayViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is display fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
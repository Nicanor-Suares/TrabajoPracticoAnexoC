package com.example.trabajopracticoanexoc;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NavigationActivityViewModel extends ViewModel {

    private MutableLiveData<Boolean> exitDialogMutable;

    public MutableLiveData<Boolean> getExitDialogMutable() {
        if (exitDialogMutable == null) {
            exitDialogMutable = new MutableLiveData<>();
        }
        return exitDialogMutable;
    }

    public void exitDialog() { exitDialogMutable.setValue(true); }

}


package com.example.appmusic;

import android.support.annotation.NonNull;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ThuVienViewModelFactory implements ViewModelProvider.Factory {
    private final String iduser;

    public ThuVienViewModelFactory(String iduser) {
        this.iduser = iduser;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ThuVienViewModel.class)) {
            return (T) new ThuVienViewModel(iduser);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}

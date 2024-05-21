package com.example.appmusic;

import static android.content.Intent.getIntent;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmusic.Activity.MainActivity;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThuVienViewModel extends ViewModel {
    private MutableLiveData<List<BaiHat>> LiveDataBaiHat;
    private List<BaiHat> baiHatList;
    private String iduser;



    public ThuVienViewModel(String iduser){
        LiveDataBaiHat = new MutableLiveData<>();
        this.iduser=iduser;
        initData(iduser);
    }


    private void initData(String iduser) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> call = dataService.GetDanhSachBaihatThuVien(iduser);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatList = response.body();
                LiveDataBaiHat.setValue(baiHatList);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    public MutableLiveData<List<BaiHat>> getBaiHatLiveData() {
        return LiveDataBaiHat;
    }

}

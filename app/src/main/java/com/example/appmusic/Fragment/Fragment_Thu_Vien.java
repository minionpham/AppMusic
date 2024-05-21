package com.example.appmusic.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appmusic.Adapter.DanhSachBaiHatAdapter;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;
import com.example.appmusic.ThuVienViewModel;
import com.example.appmusic.ThuVienViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Thu_Vien extends Fragment {
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    RecyclerView recyclerViewfavourite;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_vien, container, false);
        recyclerViewfavourite = view.findViewById(R.id.dsinfavourite);
        Getdatathuvien();
        return view;
    }

//    private void Getdatathuvien1() {
//        thuVienViewModel = new ViewModelProvider(this).get(ThuVienViewModel.class);
//        thuVienViewModel.getBaiHatLiveData().observe(getViewLifecycleOwner(), new Observer<List<BaiHat>>() {
//            @Override
//            public void onChanged(List<BaiHat> baiHats) {
//                danhSachBaiHatAdapter= new DanhSachBaiHatAdapter(getActivity(),baiHatArrayList);
//                danhSachBaiHatAdapter.updateDSbaihat((ArrayList<BaiHat>) baiHats);
//                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
//                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                recyclerViewfavourite.setHasFixedSize(true);
//                recyclerViewfavourite.setLayoutManager(linearLayoutManager);
//                recyclerViewfavourite.setAdapter(danhSachBaiHatAdapter);
//            }
//        });
//    }

    private void Getdatathuvien() {
        sharedPreferences = getContext().getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        String iduser = sharedPreferences.getString("IdUser", "0");

        DataService dataService = APIService.getService();
        Call<List<BaiHat>> call = dataService.GetDanhSachBaihatThuVien(iduser);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter= new DanhSachBaiHatAdapter(getActivity(),baiHatArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewfavourite.setHasFixedSize(true);
                recyclerViewfavourite.setLayoutManager(linearLayoutManager);
                recyclerViewfavourite.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

//    public void onResume() {
//        super.onResume();
//        Getdatathuvien();
//    }

}

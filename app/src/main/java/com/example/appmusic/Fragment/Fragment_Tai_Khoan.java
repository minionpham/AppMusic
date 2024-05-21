package com.example.appmusic.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.Activity.LoginActivity;
import com.example.appmusic.Model.TaiKhoan;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tai_Khoan extends Fragment {
    TextView tvemail,tvten;
    Button btnlogout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        tvemail=view.findViewById(R.id.tvemail);
        tvten = view.findViewById(R.id.tvten);
        btnlogout = view.findViewById(R.id.btnlogout);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        String iduser = sharedPreferences.getString("IdUser","0");
        Getdata(iduser);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void Getdata( String Iduser) {
        DataService dataService = APIService.getService();
        Call<TaiKhoan> call= dataService.GetDataTaiKhoan(Iduser);
        call.enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                TaiKhoan taiKhoan = response.body();
                tvten.setText(taiKhoan.getName());
                tvemail.setText(taiKhoan.getEmail());
            }

            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {

            }
        });




    }
}

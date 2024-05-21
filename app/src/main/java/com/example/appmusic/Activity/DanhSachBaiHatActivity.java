package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appmusic.Adapter.DanhSachBaiHatAdapter;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.Model.QuangCao;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    ImageView imgbackground,imgdsbaihat;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    QuangCao quangCao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        Anhxa();
        GetIntent();
        GetData(quangCao.getId());
        Picasso.get().load(quangCao.getHinhBaiHat()).into(imgbackground);
        Picasso.get().load(quangCao.getHinhBaiHat()).into(imgdsbaihat);
    }

    private void GetIntent() {
        Intent intent = getIntent();
        quangCao= (QuangCao) intent.getSerializableExtra("banner");
    }

    private void GetData(String idquangcao) {
        DataService dataService=APIService.getService();
        Call<List<BaiHat>> call = dataService.GetDanhSachBaiHatBanner(idquangcao);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter= new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(DanhSachBaiHatActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(danhSachBaiHatAdapter);
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

//    private void initToolbar() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }

    private void Anhxa() {
        imgbackground= findViewById(R.id.imgbackground);
        imgdsbaihat = findViewById(R.id.imgdsbaihat);
        floatingActionButton=findViewById(R.id.floatingactionbutton);
    }
}




package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.appmusic.Adapter.DanhSachBaiHatAdapter;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.Model.QuangCao;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class test extends AppCompatActivity {
    ImageView imghinh,imghinhbh;
    QuangCao quangCao;
    RecyclerView recyclerView;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        imghinh=findViewById(R.id.imghinh);
        imghinhbh = findViewById(R.id.imghinhbaihat);
        recyclerView= findViewById(R.id.recycleviewdsbaihat);

        Intent intent=getIntent();
        quangCao= (QuangCao) intent.getSerializableExtra("banner");

        Picasso.get().load(quangCao.getHinhBaiHat()).into(imghinh);
        Picasso.get().load(quangCao.getHinhBaiHat()).into(imghinhbh);
        GetData(quangCao.getId());
    }

    private void GetData(String idquangcao) {
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> call = dataService.GetDanhSachBaiHatBanner(idquangcao);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter= new DanhSachBaiHatAdapter(test.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(test.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(danhSachBaiHatAdapter);
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }
}
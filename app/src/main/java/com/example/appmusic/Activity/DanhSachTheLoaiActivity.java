package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appmusic.Adapter.DanhSachTheLoaiAdapter;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.Model.TheLoai;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewtheloai;
    ChuDe chuDe;
    DanhSachTheLoaiAdapter danhSachTheLoaiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai);
        Anhxa();
        getdataintent();
        inittoolbar();
        getdatatheloai(chuDe.getIdChuDe());
    }

    private void getdatatheloai(String idchude) {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> call = dataService.GetDanhSachtheloaitheochude(idchude);
        call.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                danhSachTheLoaiAdapter = new DanhSachTheLoaiAdapter(DanhSachTheLoaiActivity.this,theLoaiArrayList);
                recyclerViewtheloai.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiActivity.this,2));
                recyclerViewtheloai.setAdapter(danhSachTheLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });

    }

    private void getdataintent() {
        Intent intent = getIntent();
        if(intent.hasExtra("itemchude")){
            chuDe = (ChuDe) intent.getSerializableExtra("itemchude");
        }
    }

    private void inittoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_toolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbardstheloai);
        recyclerViewtheloai = findViewById(R.id.recycleviewdstheloai);
    }
}
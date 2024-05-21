package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appmusic.Adapter.DanhSachChuDeAdapter;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChuDeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewchude;
    DanhSachChuDeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);
        Anhxa();
        init();
        GetDataDsChuDe();


    }

    private void GetDataDsChuDe() {
        DataService dataService= APIService.getService();
        Call<List<ChuDe>> call = dataService.GetDanhSachChuDe();
        call.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter = new DanhSachChuDeAdapter(DanhSachChuDeActivity.this,chuDeArrayList);
                recyclerViewchude.setLayoutManager(new GridLayoutManager(DanhSachChuDeActivity.this,2));
                recyclerViewchude.setAdapter(danhSachChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbarchude);
        recyclerViewchude= findViewById(R.id.recycleviewdschude);
    }
    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chủ Đề");
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_toolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
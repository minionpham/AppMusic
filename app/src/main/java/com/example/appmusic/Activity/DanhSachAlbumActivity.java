package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appmusic.Adapter.DanhSachAlbumAdapter;
import com.example.appmusic.Model.AlbumHot;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAlbumActivity extends AppCompatActivity {
Toolbar toolbar;
RecyclerView recyclerViewalbum;
DanhSachAlbumAdapter danhSachAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_album);
        Anhxa();
        inittoolbar();
        Getdataalbum();
    }

    private void inittoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Album hot");
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_toolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Getdataalbum() {
        DataService dataService = APIService.getService();
        Call<List<AlbumHot>> call = dataService.GetDanhSachAlbum();
        call.enqueue(new Callback<List<AlbumHot>>() {
            @Override
            public void onResponse(Call<List<AlbumHot>> call, Response<List<AlbumHot>> response) {
                ArrayList<AlbumHot> albumHotArrayList = (ArrayList<AlbumHot>) response.body();
                danhSachAlbumAdapter = new DanhSachAlbumAdapter(DanhSachAlbumActivity.this,albumHotArrayList);
                recyclerViewalbum.setLayoutManager(new GridLayoutManager(DanhSachAlbumActivity.this,2));
                recyclerViewalbum.setAdapter(danhSachAlbumAdapter);

            }

            @Override
            public void onFailure(Call<List<AlbumHot>> call, Throwable t) {

            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbaralbum);
        recyclerViewalbum = findViewById(R.id.recycleviewdsalbum);
    }
}
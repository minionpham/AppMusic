package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appmusic.Adapter.DanhSachPlaylistAdapter;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView dsplaylist;
    DanhSachPlaylistAdapter danhSachPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_playlist);
        Anhxa();
        init();
        Getdata();
    }

    private void Getdata() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> call = dataService.GetDanhSachPlayList();
        call.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> playListArrayList = (ArrayList<PlayList>) response.body();
                danhSachPlaylistAdapter= new DanhSachPlaylistAdapter(DanhSachPlaylistActivity.this,playListArrayList);
                dsplaylist.setLayoutManager(new GridLayoutManager(DanhSachPlaylistActivity.this,2));
                dsplaylist.setAdapter(danhSachPlaylistAdapter);

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void Anhxa() {
        toolbar=findViewById(R.id.toolbarplaylist);
        dsplaylist=findViewById(R.id.recycleviewdsplaylist);
    }
    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_toolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
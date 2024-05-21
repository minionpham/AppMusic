package com.example.appmusic.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.appmusic.Adapter.DanhSachBaiHatAdapter;
import com.example.appmusic.Model.AlbumHot;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.Model.QuangCao;
import com.example.appmusic.Model.TheLoai;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class ListBaiHatActivity extends AppCompatActivity {
    PlayList playList;
    QuangCao quangCao;
    TheLoai theLoai;
    AlbumHot albumHot;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    ImageView imghinh;
    ArrayList<BaiHat> baiHatArrayList;
    boolean isExpanded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initUi();
        initToolbar();
        GetIntent();

        if(quangCao != null && !quangCao.getTenBaiHat().equals("")){
            initRecycleviewBanner(quangCao.getId());
            initToolbarAnimation(quangCao.getTenBaiHat(), quangCao.getHinhBaiHat());
        }

        if(playList != null && !playList.getTen().equals("")){
          initToolbarAnimation(playList.getTen(), playList.getHinhNen());
          initRecycleviewPlaylist(playList.getIdPlayList());
        }

        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            initToolbarAnimation(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            initRecycleviewTheLoai(theLoai.getIdTheLoai());
        }

        if(albumHot != null && !albumHot.getTenAlbum().equals("")){
            initToolbarAnimation(albumHot.getTenAlbum(), albumHot.getHinhAlbum());
            initRecycleviewAlbum(albumHot.getIdAlbum());
        }

        
    }

    private void initRecycleviewAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> call = dataService.GetDanhSachBaihattheoAlbum(idAlbum);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter= new DanhSachBaiHatAdapter(ListBaiHatActivity.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(ListBaiHatActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void initRecycleviewTheLoai(String idTheLoai) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> call = dataService.GetDanhSachBaihattheotheloai(idTheLoai);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter= new DanhSachBaiHatAdapter(ListBaiHatActivity.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(ListBaiHatActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void initRecycleviewPlaylist(String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> call = dataService.GetDanhSachBaihattheoplaylist(idplaylist);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter= new DanhSachBaiHatAdapter(ListBaiHatActivity.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(ListBaiHatActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }


    private void initToolbarAnimation(String ten,String hinh) {
        collapsingToolbarLayout.setTitle(ten);
       Picasso.get().load(hinh).placeholder(R.drawable.loading).into(imghinh);
       Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.loading);
       Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
           @Override
           public void onGenerated(@Nullable Palette palette) {
               int mycolor = palette.getVibrantColor(getResources().getColor(R.color.color_toolbar));
               collapsingToolbarLayout.setContentScrimColor(mycolor);
               collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.black_trans));
           }
       });

       appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
           @Override
           public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
               if(Math.abs(verticalOffset) > 200){
                   isExpanded = false;
               }
               else {
                   isExpanded = true;
               }
               invalidateOptionsMenu();
           }
       });

    }

    private void initRecycleviewBanner(String idquangcao) {
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> call = dataService.GetDanhSachBaiHatBanner(idquangcao);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter= new DanhSachBaiHatAdapter(ListBaiHatActivity.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(ListBaiHatActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangCao= (QuangCao) intent.getSerializableExtra("banner");
            }
            if(intent.hasExtra("itemplaylist")){
                playList= (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("theloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("theloai");
            }
            if(intent.hasExtra("itemalbum")){
                albumHot = (AlbumHot) intent.getSerializableExtra("itemalbum");
            }
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        floatingActionButton.setEnabled(false);
    }

    private void initUi() {
        appBarLayout= findViewById(R.id.appbarlayout);
        collapsingToolbarLayout= findViewById(R.id.CollapsingToolbarLayout);
        toolbar= findViewById(R.id.toolbar);
        floatingActionButton= findViewById(R.id.floatingactionbutton);
        recyclerView= findViewById(R.id.recycleviewdsbaihat);
        imghinh=findViewById(R.id.imghinh);
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListBaiHatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat",baiHatArrayList);
                startActivity(intent);
            }
        });
    }


    
}
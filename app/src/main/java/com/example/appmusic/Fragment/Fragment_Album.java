package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.DanhSachAlbumActivity;
import com.example.appmusic.Adapter.AlbumAdapter;
import com.example.appmusic.Model.AlbumHot;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album extends Fragment {
    View view;
    TextView tvxemthem;
    RecyclerView recyclerView;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_album,container,false);
        AnhXa();
        GetData();
        tvxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachAlbumActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void AnhXa() {
        tvxemthem = view.findViewById(R.id.tvxemthem);
        recyclerView = view.findViewById(R.id.recyclerviewAlbum);
    }

    private void GetData() {
        DataService dataService= APIService.getService();
        Call<List<AlbumHot>> call= dataService.GetDataAlbum();
        call.enqueue(new Callback<List<AlbumHot>>() {
            @Override
            public void onResponse(Call<List<AlbumHot>> call, Response<List<AlbumHot>> response) {
                ArrayList<AlbumHot> albumHotArrayList= (ArrayList<AlbumHot>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),albumHotArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(albumAdapter);
            }
            @Override
            public void onFailure(Call<List<AlbumHot>> call, Throwable t) {

            }
        });
    }
}

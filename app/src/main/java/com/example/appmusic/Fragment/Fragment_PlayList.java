package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.Activity.DanhSachPlaylistActivity;
import com.example.appmusic.Adapter.PlayListAdapter;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {
    View view;
    ViewPager viewPagerPlayList;
    CircleIndicator circleIndicator;
    PlayListAdapter playListAdapter;
    TextView tvxemthem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        Anhxa();
        GetData();
        tvxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachPlaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void Anhxa() {
        viewPagerPlayList = view.findViewById(R.id.viewpagerplaylist);
        circleIndicator= view.findViewById(R.id.indicatorplaylist);
        tvxemthem= view.findViewById(R.id.tvxemthem);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callbackplaylist = dataService.GetDataPlayList();
        callbackplaylist.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> mangplaylist = (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListAdapter(getActivity(),mangplaylist);
                viewPagerPlayList.setAdapter(playListAdapter);
                circleIndicator.setViewPager(viewPagerPlayList);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
}

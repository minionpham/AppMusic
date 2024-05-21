package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.PlayNhacActivity;
import com.example.appmusic.Adapter.PlayNhacAdapter;
import com.example.appmusic.R;

public class Fragment_Play_List_BaiHat extends Fragment {
    View view;
    RecyclerView recyclerViewplaynhac;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_list_baihat,container,false);
        recyclerViewplaynhac = view.findViewById(R.id.recycleviewlistplaybaihat);

        if(PlayNhacActivity.mangbaihat.size() >0){
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playNhacAdapter);
        }

        return view;
    }
}

package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusic.Activity.ListBaiHatActivity;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayListAdapter extends PagerAdapter {
    Context context;
    ArrayList<PlayList> playListArrayList;

    public PlayListAdapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }

    @Override
    public int getCount() {
        return playListArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playlist,null);
        ImageView imgbackgroundplaylist = view.findViewById(R.id.imgbackgroundplaylist);
        ImageView imgplaylist = view.findViewById(R.id.imageplaylist);
        TextView tvtitleplaylist = view.findViewById(R.id.titleplaylist);

        Picasso.get().load(playListArrayList.get(position).getHinhNen()).into(imgbackgroundplaylist);
        Picasso.get().load(playListArrayList.get(position).getHinhIcon()).into(imgplaylist);
        tvtitleplaylist.setText(playListArrayList.get(position).getTen());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListBaiHatActivity.class);
                intent.putExtra("itemplaylist",playListArrayList.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      container.removeView((View) object);
    }
}

package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ListBaiHatActivity;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachPlaylistAdapter extends RecyclerView.Adapter<DanhSachPlaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<PlayList> playListArrayList;

    public DanhSachPlaylistAdapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_danh_sach_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = playListArrayList.get(position);
        holder.tenplaylist.setText(playList.getTen());
        Picasso.get().load(playList.getHinhIcon()).into(holder.imgplaylist);
    }

    @Override
    public int getItemCount() {
        return playListArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgplaylist;
        TextView tenplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgplaylist = itemView.findViewById(R.id.imgplaylist);
            tenplaylist = itemView.findViewById(R.id.tvtenplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListBaiHatActivity.class);
                    intent.putExtra("itemplaylist", playListArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

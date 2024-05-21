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
import com.example.appmusic.Model.AlbumHot;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachAlbumAdapter extends RecyclerView.Adapter<DanhSachAlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<AlbumHot> albumHotArrayList;

    public DanhSachAlbumAdapter(Context context, ArrayList<AlbumHot> albumHotArrayList) {
        this.context = context;
        this.albumHotArrayList = albumHotArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumHot albumHot = albumHotArrayList.get(position);
        Picasso.get().load(albumHot.getHinhAlbum()).into(holder.imgalbum);
        holder.tvtenalbum.setText(albumHot.getTenAlbum());
        holder.tvtencasi.setText(albumHot.getTenCaSiAlbum());
    }

    @Override
    public int getItemCount() {
        return albumHotArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imgalbum;
            TextView tvtenalbum;
            TextView tvtencasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgalbum = itemView.findViewById(R.id.imagealbum);
            tvtenalbum = itemView.findViewById(R.id.tvtenalbum);
            tvtencasi = itemView.findViewById(R.id.tvtencasi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListBaiHatActivity.class);
                    intent.putExtra("itemalbum",albumHotArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
        }
    }


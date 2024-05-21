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
import com.example.appmusic.Model.TheLoai;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiAdapter extends RecyclerView.Adapter<DanhSachTheLoaiAdapter.ViewHolder>{
    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhSachTheLoaiAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_theloai,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);
        Picasso.get().load(theLoai.getHinhTheLoai()).into(holder.imgtheloai);
        holder.tvtentheloai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgtheloai;
        TextView tvtentheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtheloai = itemView.findViewById(R.id.imgtheloai);
            tvtentheloai = itemView.findViewById(R.id.tvtentheloai);
            // an vao the loai ra cac danh sach bai hat
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListBaiHatActivity.class);
                    intent.putExtra("theloai", theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

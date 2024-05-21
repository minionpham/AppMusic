package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.DanhSachTheLoaiActivity;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> chuDeArrayList;

    public DanhSachChuDeAdapter(Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_chu_de,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = chuDeArrayList.get(position);
        Picasso.get().load(chuDe.getHinhChuDe()).into(holder.imgchude);

    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imgchude);

            // su kien khi an vao tung item view chu de
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // hien thi ra cac the loai ( chu de thi gom nhieu the loai )
                    Intent intent = new Intent(context, DanhSachTheLoaiActivity.class);
                    intent.putExtra("itemchude", chuDeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

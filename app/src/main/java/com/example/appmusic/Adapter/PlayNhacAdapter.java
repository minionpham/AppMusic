package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_baihat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.tvtenbaihat.setText(baiHat.getTenBaiHat());
        holder.tvcasi.setText(baiHat.getTenCaSiBaiHat());
        holder.tvindex.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvindex,tvtenbaihat,tvcasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvindex = itemView.findViewById(R.id.tvplayindex);
            tvtenbaihat = itemView.findViewById(R.id.tvtenbaihat);
            tvcasi=itemView.findViewById(R.id.tvtencasi);
        }
    }
}

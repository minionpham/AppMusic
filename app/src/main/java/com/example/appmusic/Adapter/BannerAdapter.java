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

import com.example.appmusic.Activity.DanhSachBaiHatActivity;
import com.example.appmusic.Activity.ListBaiHatActivity;
import com.example.appmusic.Activity.test;
import com.example.appmusic.Model.QuangCao;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> quangCaoArrayList;

    public BannerAdapter(Context context, ArrayList<QuangCao> quangCaoArrayList) {
        this.context = context;
        this.quangCaoArrayList = quangCaoArrayList;
    }

    @Override
    public int getCount() {
        return quangCaoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);
        ImageView imgbackgroundbanner = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgbaihat = view.findViewById(R.id.imagebaihat);
        TextView tvtitle = view.findViewById(R.id.tvtitlebaihat);
        TextView tvgioithieu = view.findViewById(R.id.gioithieu);

        Picasso.get().load(quangCaoArrayList.get(position).getHinhAnh()).into(imgbackgroundbanner);
        Picasso.get().load(quangCaoArrayList.get(position).getHinhBaiHat()).into(imgbaihat);
        tvtitle.setText(quangCaoArrayList.get(position).getTenBaiHat());
        tvgioithieu.setText(quangCaoArrayList.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListBaiHatActivity.class);
                intent.putExtra("banner",quangCaoArrayList.get(position));
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

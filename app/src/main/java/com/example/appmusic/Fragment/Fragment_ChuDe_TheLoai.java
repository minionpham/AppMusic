package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appmusic.Activity.DanhSachChuDeActivity;
import com.example.appmusic.Activity.DanhSachTheLoaiActivity;
import com.example.appmusic.Activity.ListBaiHatActivity;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.Model.ChuDeTheLoai;
import com.example.appmusic.Model.TheLoai;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvxemthem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_chude_theloai,container,false);
        AnhXa();
        GetData();
        tvxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachChuDeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void AnhXa() {
        horizontalScrollView= view.findViewById(R.id.Scrollview);
        tvxemthem = view.findViewById(R.id.tvxemthem);
    }

    private void GetData() {
        DataService dataService= APIService.getService();
        Call<ChuDeTheLoai> callback = dataService.GetDataChuDeTheLoai();
        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeTheLoai> call, Response<ChuDeTheLoai> response) {
                ChuDeTheLoai chuDeTheLoai= response.body();

                final ArrayList<ChuDe> chuDeArrayList= new ArrayList<>();
                chuDeArrayList.addAll(chuDeTheLoai.getChuDe());
                final  ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chuDeTheLoai.getTheLoai());

                LinearLayout linearLayout= new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(480,300);
                layoutParams.setMargins(10,20,10,30);
                for(int i=0; i< chuDeArrayList.size();i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(15);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDeArrayList.get(i).getHinhChuDe() != null){
                        Picasso.get().load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    // an vao chu de thi hien thi ra cac the loai....
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiActivity.class);
                            intent.putExtra("itemchude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for(int i=0; i< theLoaiArrayList.size();i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(15);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theLoaiArrayList.get(i).getHinhTheLoai() != null){
                        Picasso.get().load(theLoaiArrayList.get(i).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    // an vao the loai ra cac ds bai hat thuoc the loai do
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListBaiHatActivity.class);
                            intent.putExtra("theloai",theLoaiArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeTheLoai> call, Throwable t) {

            }
        });
    }
}

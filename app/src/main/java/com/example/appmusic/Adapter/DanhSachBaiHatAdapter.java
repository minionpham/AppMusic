package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.PlayNhacActivity;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;
    public void updateDSbaihat(ArrayList<BaiHat> listbaihat){
        this.baiHatArrayList=listbaihat;
        notifyDataSetChanged();
    }


    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_baihat,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat= baiHatArrayList.get(position);
        Picasso.get().load(baiHat.getHinhBaiHat()).into(holder.imgbaihat);
        holder.tvtenbaihat.setText(baiHat.getTenBaiHat());
        holder.tvtencasi.setText(baiHat.getTenCaSiBaiHat());
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvtenbaihat,tvtencasi;
        ImageView imgthich,imgbaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbaihat= itemView.findViewById(R.id.imgbaihat);
            tvtenbaihat=itemView.findViewById(R.id.tvtenbaihat);
            tvtencasi= itemView.findViewById(R.id.tvtencasi);
            imgthich= itemView.findViewById(R.id.imgthich);

            // luu trang thai tym voi cac bai hat da thich
            SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
            String iduser = sharedPreferences.getString("IdUser", "0");
            DataService dataService = APIService.getService();
            Call<List<BaiHat>> call = dataService.GetDanhSachBaihatThuVien(iduser);
            call.enqueue(new Callback<List<BaiHat>>() {
                @Override
                public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                    ArrayList<BaiHat> baiHats = (ArrayList<BaiHat>) response.body();
                    for (int i=0;i<baiHats.size();i++){
                        if(baiHatArrayList.get(getPosition()).getIdBaiHat().equals(baiHats.get(i).getIdBaiHat())){
                            imgthich.setImageResource(R.drawable.iconloved);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<BaiHat>> call, Throwable t) {

                }
            });

            // su kien an nut tym
            imgthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imgthich.getDrawable().getConstantState().equals(context.getDrawable(R.drawable.iconlove).getConstantState())) {
                        imgthich.setImageResource(R.drawable.iconloved);
                        // update luot thich
                        DataService dataService = APIService.getService();
                        Call<String> call = dataService.UpdateLuotThich("1", baiHatArrayList.get(getPosition()).getIdBaiHat());
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });
                        // them vao thu vien
                        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
                        String iduser = sharedPreferences.getString("IdUser", "0");
                        Call<String> call1 = dataService.InsertintoThuVien(iduser, baiHatArrayList.get(getPosition()).getIdBaiHat());
                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String result = response.body();
                                if (result.equals("Success")) {
                                    Toast.makeText(context, "Đã thêm vào thư viện.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });

                    }
                    // th da tim va bo tim thi bo o thu vien va update lai luot thich

                    else {
                        imgthich.setImageResource(R.drawable.iconlove);
                        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
                        String iduser = sharedPreferences.getString("IdUser", "0");

                        DataService dataService = APIService.getService();
                        Call<String> call1 = dataService.DeleteFromThuVien(iduser, baiHatArrayList.get(getPosition()).getIdBaiHat());
                        Call<String> call = dataService.UpdateLuotThich("-1", baiHatArrayList.get(getPosition()).getIdBaiHat());

                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });

                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if (ketqua.equals("Success")) {
                                    Toast.makeText(context, "Đã xóa khỏi thư viện!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });

                    }
                }
            });


            // su kien phat nhac
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
    }


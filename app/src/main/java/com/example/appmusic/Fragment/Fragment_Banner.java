package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.Adapter.BannerAdapter;
import com.example.appmusic.IP;
import com.example.appmusic.Model.QuangCao;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPagerBanner;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentitem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        Anhxa();
        GetData();
        return view;
    }

    private void Anhxa() {
        viewPagerBanner= view.findViewById(R.id.viewpagerbanner);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<QuangCao>> callback = dataService.GetDataQuangCao();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banner = (ArrayList<QuangCao>) response.body();

                bannerAdapter = new BannerAdapter(getActivity(),banner);
                viewPagerBanner.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPagerBanner);
                handler= new Handler(); // qly goi run()
                runnable= new Runnable() {
                    @Override
                    public void run() {
                        currentitem = viewPagerBanner.getCurrentItem();
                        currentitem++;
                        if(currentitem >= viewPagerBanner.getAdapter().getCount()){
                            currentitem=0;
                        }
                        viewPagerBanner.setCurrentItem(currentitem,true);
                        handler.postDelayed(runnable,6000);
                    }
                };
                handler.postDelayed(runnable,6000);
            }
            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });

    }
}

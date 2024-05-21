package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmusic.Adapter.MainViewPagerAdapter;
import com.example.appmusic.Fragment.Fragment_Tai_Khoan;
import com.example.appmusic.Fragment.Fragment_Thu_Vien;
import com.example.appmusic.Fragment.Fragment_Tim_Kiem;
import com.example.appmusic.Fragment.Fragment_Trang_Chu;
import com.example.appmusic.R;
import com.example.appmusic.ThuVienViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences= getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        String iduser= sharedPreferences.getString("IdUser","0");
        AnhXa();
        InIt();
    }

    private void InIt() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Thu_Vien(),"Thư Viện");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm Kiếm");
        mainViewPagerAdapter.addFragment(new Fragment_Tai_Khoan(),"Tài Khoản");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_library_music_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.baseline_search_24);
        tabLayout.getTabAt(3).setIcon(R.drawable.baseline_account_circle_24);
    }

    private void AnhXa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appmusic.Adapter.ViewPagerPlayListNhac;
import com.example.appmusic.Fragment.Fragment_Dia_Nhac;
import com.example.appmusic.Fragment.Fragment_Play_List_BaiHat;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView tvtimesong, tvtotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPagerplaynhac;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlayListNhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_List_BaiHat fragment_play_list_baiHat;
    MediaPlayer mediaPlayer;
    int position =0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataIntent();
        init();
        eventClick();
    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(mangbaihat.size() >0){
                        fragment_dia_nhac.HinhPlayNhac(mangbaihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }
                    else{
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                    fragment_dia_nhac.objectAnimator.pause();
                }
                else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    fragment_dia_nhac.objectAnimator.resume();
                }
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat == false){
                    if(checkrandom == true){
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat=true;
                }
                else{
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkrandom == false){
                    if(repeat == true){
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom=true;
                }
                else{
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//                tvtimesong.setText(simpleDateFormat.format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() == 1){
                    imgnext.setClickable(false);
                }
               else{
                    if(mangbaihat.size() > 0){
                        if(mediaPlayer.isPlaying() || mediaPlayer != null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer=null;
                        }
                        if(position < mangbaihat.size()){
                            imgplay.setImageResource(R.drawable.iconpause);
                            position = (position+1)%mangbaihat.size();
                            if(repeat == true){
                                if(position == 0){
                                    position = mangbaihat.size()-1;
                                }
                                position -= 1;
                            }
                            if(checkrandom == true){
                                Random random = new Random();
                                int index = random.nextInt(mangbaihat.size()-1);
                                if(index == position){
                                    position = index + 1;
                                }
                                position=index;
                            }

                            new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                            fragment_dia_nhac.HinhPlayNhac(mangbaihat.get(position).getHinhBaiHat());
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                            UpdateTime();
                        }
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },4000);
                }
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() == 1){
                    imgpre.setClickable(false);
                }
                else{
                    if(mangbaihat.size() > 0){
                        if(mediaPlayer.isPlaying() || mediaPlayer != null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer=null;
                        }
                        if(position < mangbaihat.size()){
                            imgplay.setImageResource(R.drawable.iconpause);
                            position--;
                            if(repeat == true || position <0){
                                position += 1;
                            }
                            if(checkrandom == true){
                                Random random = new Random();
                                int index = random.nextInt(mangbaihat.size());
                                if(index == position){
                                    position = index+1;
                                }
                                position=index;
                            }
                            new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                            fragment_dia_nhac.HinhPlayNhac(mangbaihat.get(position).getHinhBaiHat());
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                            UpdateTime();
                        }
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },4000);
                }

            }
        });
    }

    private void GetDataIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
       if(intent != null){
           if(intent.hasExtra("cacbaihat")){
               ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
               mangbaihat = baiHatArrayList;
           }
           if(intent.hasExtra("cakhuc")){
               BaiHat baiHat = intent.getParcelableExtra("cakhuc");
               mangbaihat.add(baiHat);
           }
       }
    }

    private void init() {
        toolbarplaynhac= findViewById(R.id.toolbarplaynhac);
        tvtimesong= findViewById(R.id.tvtimesong);
        tvtotaltimesong= findViewById(R.id.tvtotaltimesong);
        sktime= findViewById(R.id.seekbarsong);
        imgplay= findViewById(R.id.imgbtnplay);
        imgrepeat= findViewById(R.id.imgbtnrepeat);
        imgnext = findViewById(R.id.imgbtnnext);
        imgpre = findViewById(R.id.imgbtnpreview);
        imgrandom = findViewById(R.id.imgbtnsuffle);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear(); // thoat ra thi dung phat nhac va clear lai mang bai hat
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);

        adapternhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        fragment_dia_nhac= new Fragment_Dia_Nhac();
        fragment_play_list_baiHat = new Fragment_Play_List_BaiHat();
        adapternhac.addFragment(fragment_play_list_baiHat);
        adapternhac.addFragment(fragment_dia_nhac);
        viewPagerplaynhac.setAdapter(adapternhac);

        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if(mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaiHat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }
    class PlayMp3 extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            }    catch(IOException e){
                    throw new RuntimeException(e);
                }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvtimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    // neu ma chay het bai hat
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        },300);

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true) {
                    if (position < mangbaihat.size()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position = (position + 1) % mangbaihat.size();
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size() - 1;
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size() - 1);
                            if (index == position) {
                                position = index + 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.HinhPlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());

                        imgpre.setClickable(false);
                        imgnext.setClickable(false);
                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imgpre.setClickable(true);
                                imgnext.setClickable(true);
                            }
                        }, 4000);
                        next=false;
                        handler1.removeCallbacks(this);
                    }
                }
                else{
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }

}
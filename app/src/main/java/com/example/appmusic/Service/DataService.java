package com.example.appmusic.Service;

import com.example.appmusic.Model.AlbumHot;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.Model.ChuDeTheLoai;
import com.example.appmusic.Model.PlayList;
import com.example.appmusic.Model.QuangCao;
import com.example.appmusic.Model.TaiKhoan;
import com.example.appmusic.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService { // gui pthuc va nhan du lieu tu phia server
    @GET("quangcao.php")
    Call<List<QuangCao>> GetDataQuangCao();

    @GET("playlist.php")
    Call<List<PlayList>> GetDataPlayList();

    @GET("chudevatheloai.php")
    Call<ChuDeTheLoai> GetDataChuDeTheLoai();

    @GET("albumhot.php")
    Call<List<AlbumHot>> GetDataAlbum();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatBanner(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachplaylist.php")
    Call<List<PlayList>> GetDanhSachPlayList();

    @FormUrlEncoded
    @POST("taikhoanngdung.php")
    Call<TaiKhoan> GetDataTaiKhoan(@Field("iduser") String iduser);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("dschude.php")
    Call<List<ChuDe>> GetDanhSachChuDe();
    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetDanhSachtheloaitheochude(@Field("idchude") String idchude);
    @GET("dsalbum.php")
    Call<List<AlbumHot>> GetDanhSachAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaihattheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich (@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("themvaothuvien.php")
    Call<String> InsertintoThuVien (@Field("iduser") String iduser, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("xoakhoithuvien.php")
    Call<String> DeleteFromThuVien (@Field("iduser") String iduser, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("dsbaihattrongthuvien.php")
    Call<List<BaiHat>> GetDanhSachBaihatThuVien(@Field("iduser") String iduser);

    @FormUrlEncoded
    @POST("searchsong.php")
    Call<List<BaiHat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);

}

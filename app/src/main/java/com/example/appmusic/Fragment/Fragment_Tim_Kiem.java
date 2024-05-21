package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adapter.SearchSongAdapter;
import com.example.appmusic.Model.BaiHat;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {

    Toolbar toolbar;
    RecyclerView recyclerViewsearch;
    TextView tvkhongcodulieu;
    SearchSongAdapter searchSongAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar = view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearch = view.findViewById(R.id.recycleviewsearchbaihat);
        tvkhongcodulieu = view.findViewById(R.id.tvkhongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);// ban chat k p la activity nen p lm nhu vay
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Tìm kiếm...");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    SearchBaiHat(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                SearchBaiHat(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchBaiHat(String tukhoa){
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> call = dataService.GetSearchBaiHat(tukhoa);
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                if(mangbaihat.size() >0){
                    searchSongAdapter= new SearchSongAdapter(getActivity(),mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsearch.setLayoutManager(linearLayoutManager);
                    recyclerViewsearch.setAdapter(searchSongAdapter);
                    tvkhongcodulieu.setVisibility(View.GONE);
                    recyclerViewsearch.setVisibility(View.VISIBLE);
                }
                else{
                    recyclerViewsearch.setVisibility(View.GONE);
                    tvkhongcodulieu.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

}

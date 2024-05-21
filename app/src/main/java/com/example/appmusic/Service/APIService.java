package com.example.appmusic.Service;

import com.example.appmusic.IP;

public class APIService {
    private  static String base_url="http://"+new IP().Ip+"/Server/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}

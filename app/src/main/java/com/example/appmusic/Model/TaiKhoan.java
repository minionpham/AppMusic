package com.example.appmusic.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TaiKhoan {

@SerializedName("Name")
@Expose
private String name;
@SerializedName("Email")
@Expose
private String email;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}
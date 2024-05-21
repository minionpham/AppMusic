package com.example.appmusic.Model;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat implements Parcelable {

@SerializedName("IdBaiHat")
@Expose
private String idBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;
@SerializedName("TenCaSiBaiHat")
@Expose
private String tenCaSiBaiHat;
@SerializedName("LinkBaiHat")
@Expose
private String linkBaiHat;
@SerializedName("LuotThich")
@Expose
private String luotThich;

    protected BaiHat(Parcel in) {
        idBaiHat = in.readString();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        tenCaSiBaiHat = in.readString();
        linkBaiHat = in.readString();
        luotThich = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    public String getIdBaiHat() {
return idBaiHat;
}

public void setIdBaiHat(String idBaiHat) {
this.idBaiHat = idBaiHat;
}

public String getTenBaiHat() {
return tenBaiHat;
}

public void setTenBaiHat(String tenBaiHat) {
this.tenBaiHat = tenBaiHat;
}

public String getHinhBaiHat() {
return hinhBaiHat;
}

public void setHinhBaiHat(String hinhBaiHat) {
this.hinhBaiHat = hinhBaiHat;
}

public String getTenCaSiBaiHat() {
return tenCaSiBaiHat;
}

public void setTenCaSiBaiHat(String tenCaSiBaiHat) {
this.tenCaSiBaiHat = tenCaSiBaiHat;
}

public String getLinkBaiHat() {
return linkBaiHat;
}

public void setLinkBaiHat(String linkBaiHat) {
this.linkBaiHat = linkBaiHat;
}

public String getLuotThich() {
return luotThich;
}

public void setLuotThich(String luotThich) {
this.luotThich = luotThich;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idBaiHat);
        dest.writeString(tenBaiHat);
        dest.writeString(hinhBaiHat);
        dest.writeString(tenCaSiBaiHat);
        dest.writeString(linkBaiHat);
        dest.writeString(luotThich);
    }
}
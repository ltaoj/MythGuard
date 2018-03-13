package cn.ltaoj.mythguard.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ltaoj on 2018/3/13 14:54.
 */

public class Signon implements Parcelable{
    private String account;
    private String pwd;
    private boolean auth;

    public Signon(String account, boolean auth) {
        this.account = account;
        this.auth = auth;
    }

    protected Signon(Parcel in) {
        account = in.readString();
        pwd = in.readString();
        auth = in.readByte() != 0;
    }

    public static final Creator<Signon> CREATOR = new Creator<Signon>() {
        @Override
        public Signon createFromParcel(Parcel in) {
            return new Signon(in);
        }

        @Override
        public Signon[] newArray(int size) {
            return new Signon[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(account);
        dest.writeString(pwd);
        dest.writeByte((byte) (auth ? 1 : 0));
    }

    public boolean isAuth() {
        return auth;
    }
}

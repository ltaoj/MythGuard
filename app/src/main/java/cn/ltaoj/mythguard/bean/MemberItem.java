package cn.ltaoj.mythguard.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2018/3/1.
 */

public class MemberItem implements Parcelable{

    private String userId;
    private String uname;
    private String type;
    private String houseNumber;
    private String IDNumber;
    private String phone;
    private String email;
    private int status;
    private String binderId;

    public MemberItem() {
    }

    protected MemberItem(Parcel in) {
        userId = in.readString();
        uname = in.readString();
        type = in.readString();
        houseNumber = in.readString();
        IDNumber = in.readString();
        phone = in.readString();
        email = in.readString();
        status = in.readInt();
        binderId = in.readString();
    }

    public static final Creator<MemberItem> CREATOR = new Creator<MemberItem>() {
        @Override
        public MemberItem createFromParcel(Parcel in) {
            return new MemberItem(in);
        }

        @Override
        public MemberItem[] newArray(int size) {
            return new MemberItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(uname);
        parcel.writeString(type);
        parcel.writeString(houseNumber);
        parcel.writeString(IDNumber);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeInt(status);
        parcel.writeString(binderId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBinderId() {
        return binderId;
    }

    public void setBinderId(String binderId) {
        this.binderId = binderId;
    }
}

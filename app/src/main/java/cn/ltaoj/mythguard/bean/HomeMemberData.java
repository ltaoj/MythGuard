package cn.ltaoj.mythguard.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lenovo on 2018/3/1.
 */

public class HomeMemberData implements Parcelable {

    private int count;
    private int page;
    private List<MemberItem> data;

    protected HomeMemberData(Parcel in) {
        count = in.readInt();
        page = in.readInt();
        data = in.createTypedArrayList(MemberItem.CREATOR);
    }

    public static final Creator<HomeMemberData> CREATOR = new Creator<HomeMemberData>() {
        @Override
        public HomeMemberData createFromParcel(Parcel in) {
            return new HomeMemberData(in);
        }

        @Override
        public HomeMemberData[] newArray(int size) {
            return new HomeMemberData[size];
        }
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MemberItem> getData() {
        return data;
    }

    public void setData(List<MemberItem> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(count);
        parcel.writeInt(page);
        parcel.writeTypedList(data);
    }
}

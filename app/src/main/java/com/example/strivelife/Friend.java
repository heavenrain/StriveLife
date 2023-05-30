package com.example.strivelife;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable {

    String name = null;
    int id = 0;

    // === Friend 屬性說明 ===
    // id: 好友id
    // nane: 好友id
    // ====================
    public Friend(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // 實作 writeToParcel 方法，將對象的數據寫入 Parcel 中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    // 實作 CREATOR 接口，用於反序列化 Parcelable 對象
    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    // 建構子，根據 Parcel 解析對象的數據
    protected Friend(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

}


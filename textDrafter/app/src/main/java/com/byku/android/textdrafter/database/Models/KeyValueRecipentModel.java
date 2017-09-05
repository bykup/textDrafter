package com.byku.android.textdrafter.database.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class KeyValueRecipentModel implements Parcelable{

    public String key;
    public String value;
    public String recipent;

    public KeyValueRecipentModel(String key, String value, String recipent) {
        this.key = key;
        this.value = value;
        this.recipent = recipent;
    }

    protected KeyValueRecipentModel(Parcel in) {
        key = in.readString();
        value = in.readString();
        recipent = in.readString();
    }

    public static final Creator<KeyValueRecipentModel> CREATOR = new Creator<KeyValueRecipentModel>() {
        @Override
        public KeyValueRecipentModel createFromParcel(Parcel in) {
            return new KeyValueRecipentModel(in);
        }

        @Override
        public KeyValueRecipentModel[] newArray(int size) {
            return new KeyValueRecipentModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(value);
        parcel.writeString(recipent);
    }
}

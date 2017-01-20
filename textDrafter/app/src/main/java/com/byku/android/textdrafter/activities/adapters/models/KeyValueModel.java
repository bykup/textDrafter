package com.byku.android.textdrafter.activities.adapters.models;

import java.util.ArrayList;
import java.util.List;

public class KeyValueModel {
    public static char INPUT = 'I';
    public static char OUTPUT = 'O';

    private String key;
    private char type;
    private String value;

    public KeyValueModel(String key, char type) {
        setKey(key);
        setType(type);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

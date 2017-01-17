package com.byku.android.textdrafter.activities.adapters.models;

import java.util.ArrayList;
import java.util.List;

public class KeyValueModel {
    public static char INPUT = 'I';
    public static char OUTPUT = 'O';

    private String key;
    private char type;
    private String value;
    private List<Integer> begPositions;
    private List<Integer> endPositions;

    public KeyValueModel(String key, char type, int begPos, int endPos) {
        setKey(key);
        setType(type);
        begPositions = new ArrayList<>();
        endPositions = new ArrayList<>();
        begPositions.add(begPos);
        endPositions.add(endPos);
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

    public void addNewPos(int begPos, int endPos){
        begPositions.add(begPos);
        endPositions.add(endPos);
    }

    public void addNewPos(List<Integer> begPos, List<Integer> endPos){
        begPositions.addAll(begPos);
        endPositions.addAll(endPos);
    }

    public List<Integer> getBegPositions(){
        return begPositions;
    }

    public List<Integer> getEndPositions(){
        return endPositions;
    }
}

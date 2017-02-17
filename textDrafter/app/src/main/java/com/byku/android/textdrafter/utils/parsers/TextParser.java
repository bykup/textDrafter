package com.byku.android.textdrafter.utils.parsers;

import android.text.TextUtils;

import com.byku.android.textdrafter.activities.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.utils.parsers.textparser.UniqueKeyValueList;

import java.util.List;

public class TextParser {

    private static boolean ifPrevMathOp = false;
    private char mathOp[] = {'+','-'};

    public List<KeyValueModel> textToKeyValue(String smsText){
        UniqueKeyValueList uniqueKeyValueList = new UniqueKeyValueList();
        int beg,end;

        beg = smsText.indexOf("[");
        while(beg != -1){
            end = smsText.indexOf("]",beg);
            if(end == -1)
                return uniqueKeyValueList.returnList();
            String key = smsText.substring(beg+1,end);
            if(ifValueInput(key))
                uniqueKeyValueList.add(new KeyValueModel(key,KeyValueModel.INPUT));
            else if(ifValueOutput(key))
                uniqueKeyValueList.add(new KeyValueModel(key,KeyValueModel.OUTPUT));
            beg = smsText.indexOf("[",end);
        }
        return uniqueKeyValueList.returnList();
    }

    private boolean ifValueInput(String string){
        if(string.isEmpty())
            return false;
        for(char c : string.toCharArray()){
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean ifValueOutput(String string){
        if(TextUtils.isEmpty(string))
            return false;
        boolean ifHasMathOperations = false;
        if(!Character.isDigit(string.charAt(0)) || !Character.isDigit(string.charAt(string.length()-1)))
            return false;
        if(string.isEmpty())
            return false;
        for(char c : string.toCharArray()){
            if(Character.isLetter(c))
                return false;
            if(ifCharMathOperation(c)) {
                if(ifPrevMathOp)
                    return false;
                ifPrevMathOp = true;
                ifHasMathOperations = true;
            }else{
                ifPrevMathOp = false;
            }
        }
        return ifHasMathOperations;
    }

    private boolean ifCharMathOperation(char c){
        for (char m : mathOp){
            if(c==m)
                return true;
        }
        return false;
    }

}

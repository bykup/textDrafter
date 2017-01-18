package com.byku.android.textdrafter.utils.parsers;

import com.byku.android.textdrafter.activities.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.utils.parsers.textparser.UniqueKeyValueList;

import java.util.List;

public class TextParser {

    //detects [1] [2] [3] or [2+3]
    public static List<KeyValueModel> textToKeyValue(String smsText){
        UniqueKeyValueList uniqueKeyValueList = new UniqueKeyValueList();
        int beg,end;

        beg = smsText.indexOf("[");
        while(beg != -1){
            end = smsText.indexOf("]",beg);
            if(end == -1)
                return uniqueKeyValueList.returnList();
            String key = smsText.substring(beg+1,end);
            if(ifValueInput(key))
                uniqueKeyValueList.add(new KeyValueModel(key,KeyValueModel.INPUT,beg+1,end));
            else if(ifValueOutput(key))
                uniqueKeyValueList.add(new KeyValueModel(key,KeyValueModel.OUTPUT,beg+1,end));
            beg = smsText.indexOf("[",end);
        }
        return uniqueKeyValueList.returnList();
    }

    private static boolean ifValueInput(String string){
        if(string.isEmpty())
            return false;
        for(char c : string.toCharArray()){
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static boolean ifValueOutput(String string){
        boolean ifHasMathOperations = false;
        if(string.isEmpty())
            return false;
        for(char c : string.toCharArray()){
            if(Character.isLetter(c))
                return false;
            if(ifCharMathOperation(c))
                ifHasMathOperations = true;
        }
        return ifHasMathOperations;
    }

    private static boolean ifCharMathOperation(char c){
        if(c == '+' || c == '-'){
            return true;
        }
        return false;
    }

}

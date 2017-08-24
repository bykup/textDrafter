package com.byku.android.textdrafter.utils.parsers;

import android.text.TextUtils;
import android.widget.EditText;

public class ContactTelParserImpl implements ContactTelParser{

    private StringBuilder builder;
    private String textToOperateOn;
    private String operationResult;

    public ContactTelParserImpl(EditText editText){
        this.textToOperateOn = editText.getText().toString();
        this.builder = new StringBuilder();
    }

    public ContactTelParserImpl(String textToOperateOn){
        this.textToOperateOn = textToOperateOn;
        this.builder = new StringBuilder();
    }

    @Override
    public ContactTelParser append(String textToAppend){
        builder.append(textToOperateOn);
        if(TextUtils.isEmpty(textToOperateOn) || textToOperateOn.endsWith(";"))
            builder.append(textToAppend);
        else
            builder.append(";").append(textToAppend);
        operationResult = builder.toString();

        return this;
    }

    @Override
    public String getOperationResult(){
        if(TextUtils.isEmpty(operationResult))
            return textToOperateOn;
        else
            return operationResult;
    }

}

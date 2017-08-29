package com.byku.android.textdrafter.activities.mainactivity.activity;

import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;
import com.byku.android.textdrafter.database.SmsTextDbHelperInterface;

import java.util.List;

public class MainPresenter implements MainPresenterInterface {

    private SmsTextDbHelperInterface smsTextDbHelper;
    private MainViewInterface mainView;
    private List<KeyValueRecipentModel> modelList;

    public MainPresenter(SmsTextDbHelperInterface smsTextDbHelper) {
        this.smsTextDbHelper = smsTextDbHelper;
    }

    @Override
    public MainPresenterInterface onCreate(MainViewInterface mainView) {
        this.mainView = mainView;
        return this;
    }

    @Override
    public MainPresenterInterface refresh() {
        initViewsAndList();
        return this;
    }

    @Override
    public MainPresenterInterface removeKey(String key) {
        smsTextDbHelper.removeFromDatabase(key);
        return this;
    }

    private void initViewsAndList() {
//        List<String> wot = smsTextDbHelper.readAllKeysFromDb();
        modelList = smsTextDbHelper.readAllFullModelsFromDatabase();
        mainView.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainView.setItemList(modelList);
            }
        });
    }
}

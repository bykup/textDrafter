package com.byku.android.textdrafter.activities.mainactivity.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.byku.android.textdrafter.activities.mainactivity.adapters.models.ContactModel;
import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.utils.ContactDbHelper;
import com.byku.android.textdrafter.utils.PermissionHelper;
import com.byku.android.textdrafter.utils.parsers.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainFragmentListeners {

    public TextWatcher getTextWatcherTelText(final MainFragmentModel model) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (PermissionHelper.hasContactPermissions(model.getActivity()))
                    new ContactDbHelper().getContacts(model.getActivity());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                model.setTelText(editable.toString());
            }
        };
    }

    public TextWatcher getTextWatcherSmsText(final MainFragmentModel model, final MainRecycler recycler) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                model.setSmsText(editable.toString());
                recycler.getList().clear();
                recycler.getList().addAll(new TextParser().textToKeyValue(model.getSmsText()));
                recycler.getSmsValuesAdapterImpl().setList(recycler.getList());
            }
        };
    }

    public View.OnFocusChangeListener getFocusChangeListener(final FragmentView fragmentView) {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (view.isFocused()) {
                    Log.d("MainFragmentListeners", "EditText focused");
                    Observable.fromCallable(new Callable<List<ContactModel>>() {
                        @Override
                        public List<ContactModel> call() throws Exception {
                            if (PermissionHelper.hasContactPermissions(fragmentView.getActivity()))
                                return new ContactDbHelper().getContacts(fragmentView.getActivity());
                            else return new ArrayList<ContactModel>();
                        }
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnNext(new Consumer<List<ContactModel>>() {
                                @Override
                                public void accept(@NonNull List<ContactModel> models) throws Exception {
                                    if (models.isEmpty())
                                        fragmentView.setViewVisibility(0, View.GONE);
                                    else {
                                        fragmentView.setViewVisibility(0, View.VISIBLE);
                                        fragmentView.onContatsListReady(models);
                                    }
                                }
                            })
                            .subscribe();

                } else {
                    fragmentView.setViewVisibility(0, View.GONE);
                }
            }
        };
    }

}

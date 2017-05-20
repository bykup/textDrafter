package com.byku.android.textdrafter.activities.mainactivity.adapters.handler;

import android.view.View;

import com.byku.android.textdrafter.activities.mainactivity.adapters.models.ContactModel;
import com.byku.android.textdrafter.activities.mainactivity.fragment.FragmentView;

public class ContactHandlers {

    public static View.OnClickListener getOnContactClick(final ContactModel contact, final FragmentView  fragmentView){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentView.setCurrentContact(contact);
            }
        };
    }

}

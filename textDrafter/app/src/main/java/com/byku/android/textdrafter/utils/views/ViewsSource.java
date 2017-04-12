package com.byku.android.textdrafter.utils.views;

import android.view.View;

import com.byku.android.textdrafter.utils.views.observers.ViewDimensions;

public interface ViewsSource {

    View getRestrictingView();

    void setButtonWidth(ViewDimensions viewDimensions);

}

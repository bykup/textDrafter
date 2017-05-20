package com.byku.android.textdrafter.utils.views;

import android.view.ViewTreeObserver;

import com.byku.android.textdrafter.utils.views.observers.ViewDimensions;

public class ViewSizeCalculatorImpl implements ViewSizeCalculator, ViewTreeObserver.OnGlobalLayoutListener{

    ViewsSource source;
    int amountToShow;


    public ViewSizeCalculatorImpl(ViewsSource source) {
        this.source = source;
        this.amountToShow = 1;
    }

    @Override
    public void calcViewSize() {
        source.getRestrictingView().getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        source.setButtonWidth(new ViewDimensions(source.getRestrictingView().getWidth(), source.getRestrictingView().getHeight()));
        source.getRestrictingView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}

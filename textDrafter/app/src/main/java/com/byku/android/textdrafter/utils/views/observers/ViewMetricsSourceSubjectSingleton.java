package com.byku.android.textdrafter.utils.views.observers;

import java.util.HashSet;
import java.util.Set;

public class ViewMetricsSourceSubjectSingleton {

    private static ViewMetricsSourceSubjectSingleton subject;

    private Set<ViewMetricsSourceObserver> observersSet;
    private ViewDimensions viewDimensions;

    private ViewMetricsSourceSubjectSingleton(){
        observersSet = new HashSet<ViewMetricsSourceObserver>();
        viewDimensions = new ViewDimensions(0,0);
    };

    public static ViewMetricsSourceSubjectSingleton getInstance(){
        if(subject == null){
            subject = new ViewMetricsSourceSubjectSingleton();
        }
        return subject;
    }

    public boolean areDimensionsReady(){
        return viewDimensions.height != 0 && viewDimensions.width != 0;
    }

    public ViewDimensions getViewDimensions() {
        return viewDimensions;
    }

    public void setAddKeyDimensions(ViewDimensions viewDimensions) {
        this.viewDimensions = viewDimensions;
        notifyAllObservers();
    }

    public void attach(ViewMetricsSourceObserver observer) {
        observersSet.add(observer);
    }

    public void detach(ViewMetricsSourceObserver observer){
        observersSet.remove(observer);
    }

    public void notifyAllObservers() {
        for(ViewMetricsSourceObserver observer : observersSet){
            observer.update(viewDimensions);
        }
    }
}

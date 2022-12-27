package com.mahendra249k.equalizer_booster.utils;

import java.util.Observable;

public class ObservableObject extends Observable {
    private static ObservableObject instance = new ObservableObject();

    public static ObservableObject getInstance() {
        return instance;
    }

    private ObservableObject() {
    }

    public void updateValue(Object obj) {
        synchronized (this) {
            setChanged();
            notifyObservers(obj);
        }
    }
}

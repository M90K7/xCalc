package com.mk7.xcalc.presenters;

import java.util.Vector;

/**
 * Created by MK7 on 9/11/2015.
 */
public class Settings {

    static Settings instance;

    static {
        instance = new Settings();
    }

    private boolean isShiftMode = false;
    private transient Vector<SettingListener> listeners = new Vector();

    private Settings() {
    }

    public boolean isShiftMode() {
        return isShiftMode;
    }

    public void setIsShiftMode(boolean isShiftMode) {
        this.isShiftMode = isShiftMode;
        firedIsShiftMode();
    }

    public static Settings getInstance() {
        return instance;
    }

    public synchronized void addListener(SettingListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(SettingListener listener) {
        listeners.remove(listener);
    }

    void firedIsShiftMode() {
        for (SettingListener listener : listeners) {
            listener.isShiftChanged(this);
        }

    }
}

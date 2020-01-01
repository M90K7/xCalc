package com.mk7.xcalc.presenters;

import java.util.EventListener;

public interface SettingListener extends EventListener {

    void isShiftChanged(Settings settings);
}

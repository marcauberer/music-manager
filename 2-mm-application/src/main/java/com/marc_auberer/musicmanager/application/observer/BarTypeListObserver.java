package com.marc_auberer.musicmanager.application.observer;

import com.marc_auberer.musicmanager.domain.bartype.BarType;

import java.util.List;

public interface BarTypeListObserver {
    void onBarTypeListChanged(List<BarType> barTypeList);
}

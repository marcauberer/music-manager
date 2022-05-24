package com.marc_auberer.musicmanager.adapters;

public class BarTypeUiModel {

    private final long id;
    private final int beatCount; // e.g. for 6/8: 6
    private final int beatValue; // e.g. for 6/8: 8

    public BarTypeUiModel(long id, int beatCount, int beatValue) {
        this.id = id;
        this.beatCount = beatCount;
        this.beatValue = beatValue;
    }

    public long getId() {
        return id;
    }

    public int getBeatCount() {
        return beatCount;
    }

    public int getBeatValue() {
        return beatValue;
    }

    @Override
    public String toString() {
        return beatCount + "/" + beatValue;
    }
}

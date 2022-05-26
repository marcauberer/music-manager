package com.marc_auberer.musicmanager.domain.bartype;

public class BarType {

    private final long id;
    private final int beatCount; // e.g. for 6/8: 6
    private final int beatValue; // e.g. for 6/8: 8

    public BarType(long id, int beatCount, int beatValue) {
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

    public String[] getFieldContents() {
        return new String[] {String.valueOf(id), String.valueOf(beatCount), String.valueOf(beatValue)};
    }

    public static String[] getCSVHeader() {
        return new String[] {"Id", "BeatCount", "BeatValue"};
    }
}
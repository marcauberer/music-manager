package com.marc_auberer.musicmanager.db;

public abstract class Repository {

    public static final long AUTO_INC = -1;

    protected abstract void writeOut();

    protected abstract void reload();
}
